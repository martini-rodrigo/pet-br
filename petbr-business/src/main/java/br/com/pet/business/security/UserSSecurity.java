/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Value;

// Create immutable objects
@Value
// Object Creation pattern
@Builder
public class UserSSecurity implements UserDetails {

    /** The Constant serialVersionUID. */
    private static final long                      serialVersionUID = -8986728347833642033L;

    /** The user id. */
    private String                                 id;

    /** The login from user. */
    private String                                 username;

    /** The password from user. */
    private String                                 password;

    /** The profiles from user. */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Gets the profiles from user.
     *
     * @return the profiles
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    /**
     * Gets the login.
     *
     * @return the login
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * The account is not expired
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * The account is not locked
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * The credentials is not expired
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * User is active
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
