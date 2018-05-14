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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/public/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final EventService eventService;

    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
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


    @GetMapping("principal")
    public ResponseEntity getPrincipal(Principal principal) {
        if (principal == null) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("name", "N/A");
            return new ResponseEntity(map, HttpStatus.OK);
        } else {
            return new ResponseEntity(principal,HttpStatus.OK);
        }
    }

    @PostMapping("/send")
    public void sendUser(@RequestBody User user) {
        logger.info("user: {}", user);
    }

}
