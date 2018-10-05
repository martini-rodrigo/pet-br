/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.service;

import java.util.Optional;

import br.com.pet.business.param.AuthParam;
import br.com.pet.business.security.UserSSecurity;

public interface UserAuthenticationService {

    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param param
     * @return an token
     */
    Optional<String> login(AuthParam param);


    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(UserSSecurity user);
}
