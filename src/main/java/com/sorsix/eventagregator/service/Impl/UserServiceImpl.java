package com.sorsix.eventagregator.service.Impl;

import com.sorsix.eventagregator.model.User;
import com.sorsix.eventagregator.model.UserDetails;
import com.sorsix.eventagregator.model.enums.Provider;
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
    public User createOrFindUser(Authentication authentication, String requestURI) {
        Map userDetailsMap = convertAuthDetailsToMap(authentication);
        String email = userDetailsMap.get("email").toString();
        return userRepository.findById(email)
                .map(user -> {
                    logger.info("User has been found {}", user);
                    return user;
                })
                .orElseGet(() -> {
                    logger.info("Creating user with OAuth2 Provider Details");
                    String name = userDetailsMap.get("name").toString();
                    String authId = userDetailsMap.get("id").toString();
                    Provider provider = findProviderFromURI(requestURI);
                    User user = new User(email);
                    UserDetails userDetails = UserDetails.createFromMapValues(authId, name, user, provider);
                    userDetailsRepository.save(userDetails);
                    user.setUserDetails(userDetails);
                    logger.info("Finished creating user with OAuth2 Provider Details: {}", user);
                    return userRepository.save(user);
                });
    }

    private Map convertAuthDetailsToMap(Authentication authentication) {
        OAuth2Authentication auth = (OAuth2Authentication) authentication;
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(auth.getUserAuthentication().getDetails(), Map.class);
    }

    private Provider findProviderFromURI(String requestURI) {
        if (requestURI.contains("github"))
            return Provider.GITHUB;
        else if (requestURI.contains("facebook"))
            return Provider.GOOGLE;
        else
            return Provider.FACEBOOK;
    }


}
