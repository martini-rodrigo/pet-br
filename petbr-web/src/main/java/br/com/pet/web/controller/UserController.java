/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pet.business.security.UserSecurity;
import br.com.pet.web.security.AuthenticatedUser;


@RestController
@RequestMapping("/users")
public class UserController {
    
    /**
     * Gets the current authenticated user.
     *
     * @return authenticated user
     */
    @GetMapping("/authenticated")
    public ResponseEntity<?> getAuthenticated(@AuthenticatedUser final UserSecurity user) {
        
        return new ResponseEntity<UserSecurity>(user, HttpStatus.OK);
    }
}
