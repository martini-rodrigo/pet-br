/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pet.business.security.UserSSecurity;
import br.com.pet.dal.model.UserModel;
import br.com.pet.dal.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

    
    /** The user repository. */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Find user details.
     * 
     * @param username 
     * @return UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Find user model
        UserModel model = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found."));
       
        // Create security user
        return UserSSecurity.builder()
                .id(model.getUserId())
                .username(model.getUsername())
                .password(model.getPassword())
                .authorities(new ArrayList<GrantedAuthority>())
                .build();
    }

}
