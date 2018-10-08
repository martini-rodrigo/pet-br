/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableMap;

import br.com.pet.business.param.AuthParam;
import br.com.pet.business.security.UserSecurity;
import br.com.pet.dal.repository.UserRepository;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TokenService tokenService;
    
    @Override
    public Optional<String> login(AuthParam param) {
        
        return userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword())
                // Create a new token
                .map(user -> tokenService.newToken(ImmutableMap.of("username", user.getUsername())));
    }

    @Override
    public void logout(UserSecurity user) {
        // TODO Auto-generated method stub
    }


}
