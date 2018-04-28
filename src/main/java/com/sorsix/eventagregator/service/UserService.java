package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserById(Long id);

}
