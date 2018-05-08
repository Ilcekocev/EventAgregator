package com.sorsix.eventagregator.service;

import com.sorsix.eventagregator.model.User;
import com.sorsix.eventagregator.model.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public interface UserService {

    Optional<User> findUserById(String id);

    Optional<User> login(Authentication authentication);

    User createOrFindUser(String mail, Map details);

    UserDetails createUserDetails(Map details);

}
