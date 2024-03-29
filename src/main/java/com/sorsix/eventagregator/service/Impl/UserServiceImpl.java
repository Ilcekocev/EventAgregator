package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.User;
import com.sorsix.eventagregator.model.UserDetails;
import com.sorsix.eventagregator.repository.UserDetailsRepository;
import com.sorsix.eventagregator.repository.UserRepository;
import com.sorsix.eventagregator.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    public UserServiceImpl(UserRepository userRepository, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> login(Authentication authentication) {
        Map userDetailsMap = convertAuthDetailsToMap(authentication);
        Optional<String> email = Optional.ofNullable((String) userDetailsMap.get("email"));
        return Optional.ofNullable(email.map(mail -> createOrFindUser(mail, userDetailsMap))
                .orElseGet(() -> {
                    String name = authentication.getName();
                    return createOrFindUser(name, userDetailsMap);
                }));
    }

    @Override
    public User createOrFindUser(String id, Map details) {
        return userRepository.findById(id)
                .map(user -> {
                    logger.info("User has been found {}", user);
                    return user;
                })
                .orElseGet(() -> {
                    logger.info("Creating user with OAuth2 Provider Details");
                    UserDetails userDetails = createUserDetails(details);
                    User user = new User(id, userDetails);
                    logger.info("Finished creating user with OAuth2 Provider Details: {}", user);
                    return userRepository.save(user);
                });
    }

    @Override
    public UserDetails createUserDetails(Map details) {
        logger.info("Creating user details");
        String name = details.get("name").toString();
        String avatar = extractAvatar(details);
        logger.info("avatar: {}", avatar);
        UserDetails userDetails = new UserDetails(name, avatar);
        logger.info("Saving user details for {}", name);
        return userDetailsRepository.save(userDetails);
    }

    public Map convertAuthDetailsToMap(Authentication authentication) {
        OAuth2Authentication auth = (OAuth2Authentication) authentication;
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.convertValue(auth.getUserAuthentication().getDetails(), Map.class);
        logger.info("Printing authentication map: {}", map);
        return map;
    }

    private String extractAvatar(Map details) {
        return Optional.ofNullable(details.get("picture"))
                .map(String.class::cast)
                .orElseGet(() -> (String) details.get("avatar_url"));
    }

}
