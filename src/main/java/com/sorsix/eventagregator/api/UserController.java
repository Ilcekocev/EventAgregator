package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.model.User;
import com.sorsix.eventagregator.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> login(Authentication authentication, HttpServletRequest request) {
        return userService.login(authentication, request.getRequestURI())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

}
