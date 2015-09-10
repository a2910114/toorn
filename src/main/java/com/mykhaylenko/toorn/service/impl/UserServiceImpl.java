package com.mykhaylenko.toorn.service.impl;

import com.mykhaylenko.toorn.model.User;
import com.mykhaylenko.toorn.repository.UserRepository;
import com.mykhaylenko.toorn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pavlo.mykhaylenko on 8/14/2015.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String username) {
        if (username != null) {
            return userRepository.findByUsername(username);
        }

        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
