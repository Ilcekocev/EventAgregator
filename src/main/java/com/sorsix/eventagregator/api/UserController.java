package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.model.User;
import com.sorsix.eventagregator.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> login(Authentication authentication, HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        // logger.info("{}", bearerTokenExtractor.extract(request).getDetails());
//        logger.info("{}", bearerTokenExtractor.extract(request).isAuthenticated());
//        logger.info("{}", authentication.isAuthenticated());
        logger.info("{}", getMap(authentication).toString());
        logger.info(authHeader);
        return userService.login(authentication)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @GetMapping
    public Map getMap(Authentication authentication) {
        logger.info("In getMap");
        Map<String, String> map = new LinkedHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        if (authentication != null) {
            return objectMapper.convertValue(authentication.getDetails(), Map.class);
        }
        map.put("name", "N/A");
        return map;
    }

    @RequestMapping("/token")
    private ResponseEntity<String> getToken(Authentication authentication) {
        Optional<String> opt = Optional.of((String) getMap(authentication).get("tokenValue"));
        return opt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
