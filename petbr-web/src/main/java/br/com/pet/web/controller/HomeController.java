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
import org.springframework.web.bind.annotation.RestController;

import br.com.pet.business.service.UserService;

@RestController
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<?> home() throws Exception {

        return new ResponseEntity<String>("Welcome " + UserService.authenticated().getUsername(), HttpStatus.OK);
    }
}
