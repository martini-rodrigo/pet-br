/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.web.config;

import static org.springframework.http.HttpStatus.FORBIDDEN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.pet.web.security.NoRedirectStrategy;
import br.com.pet.web.security.TokenAuthenticationFilter;
import br.com.pet.web.security.TokenAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    
    /** automatically initiates the TokenAuthenticationProvider. */
    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;
    
    /**
     * URLs excluded from security.
     */
    private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/auth")
    );
    
    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);
    
    /**
     * AuthenticationManager bean.
     */
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    /**
     * Configuration spring security.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Add CORS filter
        http
        .cors()
        
        // No session will be created by Spring Security
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        
        // If a user try to access a resource without having enough permissions
        .and()
        .exceptionHandling()
        
        // This entry point handles when you request a protected page and you are not yet authenticated
        .defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
        .and()
        .authenticationProvider(tokenAuthenticationProvider)
        // Add custom JWT security filter to capture any authentication token
        .addFilterBefore(restAuthenticationFilter(), AnonymousAuthenticationFilter.class)
        .authorizeRequests()
        .requestMatchers(PROTECTED_URLS)
        .authenticated()
        
        // Methods disabled
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .logout().disable();
    }
    
    /**
     * URLs excluded from security.
     */
    @Override
    public void configure(final WebSecurity web) {
      web.ignoring().requestMatchers(PUBLIC_URLS);
    }
    
    /**
     * The TokenAuthenticationFilter is registered in Spring Security to capture any authentication token.
     */
    @Bean
    TokenAuthenticationFilter restAuthenticationFilter() throws Exception {
        
      final TokenAuthenticationFilter filter = new TokenAuthenticationFilter(PROTECTED_URLS);
      filter.setAuthenticationManager(authenticationManager());
      filter.setAuthenticationSuccessHandler(successHandler());
      return filter;
    }
    
    @Bean
    SimpleUrlAuthenticationSuccessHandler successHandler() {
        
      final SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
      successHandler.setRedirectStrategy(new NoRedirectStrategy());
      return successHandler;
    }
    
    @Bean
    public AuthenticationEntryPoint forbiddenEntryPoint() {
      return new HttpStatusEntryPoint(FORBIDDEN);
    }
    
    /**
     * Configuration user service.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(tokenAuthenticationProvider);
    }
    
    /**
     * CORS configuration.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
    
    /**
     * Encrypt the password.
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
