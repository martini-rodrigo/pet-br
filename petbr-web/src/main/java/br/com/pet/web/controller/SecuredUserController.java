/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pet.business.security.UserSSecurity;
import br.com.pet.business.service.UserAuthenticationService;

/**
 * @author rodrigo
 * 
 * The SecuredUserController allows the user to perform operations only when connected
 *
 */
@RestController
@RequestMapping("/security-users")
public class SecuredUserController {

    /** automatically initiates the UserAuthenticationService. */
    @Autowired
    private UserAuthenticationService authentication;
    
    
    /**
     * Get the current user
     *
     * @return current user
     */
    @GetMapping("/current")
    public ResponseEntity<?> getCurrent(@AuthenticationPrincipal final UserSSecurity loggedUser) {
        return new ResponseEntity<UserSSecurity>(loggedUser, HttpStatus.OK);
    }
    
    /**
     * Logout
     *
     * @return success
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout(@AuthenticationPrincipal final UserSSecurity user) {
        
        // Logout
        authentication.logout(user);
        
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
