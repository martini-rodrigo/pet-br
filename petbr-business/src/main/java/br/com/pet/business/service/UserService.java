/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.service;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.pet.business.security.UserSecurity;

public interface UserService {

    /**
     * Gets the current authenticated user.
     *
     * @return authenticated user
     */
     static UserSecurity authenticated() {
        
        // Get authenticated user
        return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
