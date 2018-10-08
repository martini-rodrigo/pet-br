/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.dal.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.pet.dal.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
  
    @Transactional(readOnly = true)
    Optional<UserModel> findByUsername(String username);
    
    @Transactional(readOnly = true)
    Optional<UserModel> findByUsernameAndPassword(String username, String password);
    
    @Transactional(readOnly = true)
    Optional<UserModel> findByUserId(String userId);
}
