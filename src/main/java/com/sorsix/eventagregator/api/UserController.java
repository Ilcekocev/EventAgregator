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

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/public/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> login(Authentication authentication) {
        return userService.login(authentication)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public Map convertAuthDetailsToMap(Authentication authentication) {
        logger.info("In getMap");
        if (authentication != null) {
            return userService.convertAuthDetailsToMap(authentication);
        }
        Map<String, String> map = new LinkedHashMap<>();
        map.put("email", "N/A");
        return map;
    }

}
