/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.business.param;

import lombok.Data;
import lombok.NonNull;

@Data
public class AuthParam {

    /** The username. */
    private @NonNull  String username;

    /** The password. */
    private @NonNull  String password;
}
