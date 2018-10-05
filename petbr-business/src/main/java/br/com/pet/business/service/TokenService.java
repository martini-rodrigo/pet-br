/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.service;

import java.util.Map;

public interface TokenService {

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> verify(String token);
    
    /**
     * Create a new token.
     *
     * @param attributes
     * @return new token
     */
    String newToken(final Map<String, String> attributes);
}
