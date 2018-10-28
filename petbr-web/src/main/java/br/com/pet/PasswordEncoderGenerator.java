/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

    public static void main(String[] args) {
    	
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("nimda"));
        System.out.println(passwordEncoder.matches("nimda", "$2a$10$x9Xo8eAvtOahZ1UGJJT0Fu8SByGZrgvgM4ssf59pxsoLPjtvMwmb."));
    }
}
