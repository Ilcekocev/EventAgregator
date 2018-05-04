package com.sorsix.eventagregator.api;

import com.sorsix.eventagregator.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //Get info about the user
    @GetMapping
    public Principal user(OAuth2Authentication oAuth2Authentication) {
        logger.info("{}", oAuth2Authentication.getCredentials());
        logger.info("{}", oAuth2Authentication.getUserAuthentication());
        logger.info("{}", oAuth2Authentication.getDetails());
        logger.info("{}", oAuth2Authentication.getName());
        logger.info("{}", oAuth2Authentication.getUserAuthentication().getCredentials());
        return oAuth2Authentication;
    }



}
