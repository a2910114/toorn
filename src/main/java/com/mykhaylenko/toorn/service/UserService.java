package com.mykhaylenko.toorn.service;

import com.mykhaylenko.toorn.model.User;

/**
 * Created by pavlo.mykhaylenko on 8/14/2015.
 */
public interface UserService {

    User save(User user);

    User findByUserName(String username);

    User findByEmail(String email);
}
