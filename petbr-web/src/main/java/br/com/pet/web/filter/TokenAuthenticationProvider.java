/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.web.filter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.pet.business.service.TokenService;

/**
 * @author rodrigo
 *
 * The TokenAuthenticationProvider is responsible for finding the user by authentication token.
 */
@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    /** automatically initiates the TokenService. */
    @Autowired
    private TokenService tokenService;
    
    /** automatically initiates the UserDetailsService. */
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    /**
     * Constructor
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }
    
    /**
     * Find the user by authentication token 
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        
        // Get token
        final Object token = authentication.getCredentials();
        
        return Optional
          // Checks the validity of the given credentials
          .ofNullable(tokenService.verify(String.valueOf(token)))
          // Get the username from token
          .map(map -> map.get("username"))
          // Finds a authentication user  
          .map(userDetailsService::loadUserByUsername)
          // Exception
          .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
    }

}
