/*
* Copyright (c) 1923 - 2016 Leroy Merlin. All rights reserved.
*
* It's content can not be copied and/or distributed
* without the express permission
*/
package br.com.pet.dal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@Document(collection = "user")
public class UserModel {

    /** The user id. */
    @Id
    private @NonNull String userId;

    /** The name user. */
    private @NonNull String          name;

    /** The username. */
    private @NonNull String          username;

    /** The password. */
    private @NonNull String          password;

}
