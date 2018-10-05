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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pet.business.param.AuthParam;
import br.com.pet.business.service.UserAuthenticationService;

@RestController
public class AuthController {

    /** automatically initiates the UserAuthenticationService. */
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    
    
    /**
     * Authentic user in the system
     * 
     * @param authentication data
     * @return token
     */
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthParam param) throws Exception {

        return new ResponseEntity<String>(userAuthenticationService.login(param)
                .orElseThrow(() -> new RuntimeException("invalid login and/or password")), HttpStatus.OK);
    }
}
