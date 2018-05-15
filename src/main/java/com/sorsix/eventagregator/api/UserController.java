package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.model.Event;
import com.sorsix.eventagregator.model.User;
import com.sorsix.eventagregator.service.EventService;
import com.sorsix.eventagregator.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<User> login(Authentication authentication) {
        return userService.login(authentication)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping("/map")
    public Map convertAuthDetailsToMap(Authentication authentication) {
        if (authentication != null) {
            return userService.convertAuthDetailsToMap(authentication);
        }
        return Collections.singletonMap("id", "N/A");
    }

}
