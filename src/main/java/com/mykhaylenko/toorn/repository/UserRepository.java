package com.mykhaylenko.toorn.repository;

import com.mykhaylenko.toorn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pavlo.mykhaylenko on 8/28/2015.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
}
