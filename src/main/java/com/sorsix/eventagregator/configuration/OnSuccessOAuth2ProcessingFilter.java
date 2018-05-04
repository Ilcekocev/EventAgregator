package com.sorsix.eventagregator.configuration;

import com.sorsix.eventagregator.service.UserService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class OnSuccessOAuth2ProcessingFilter extends OAuth2ClientAuthenticationProcessingFilter {
    private static final Logger logger = LoggerFactory.getLogger(OnSuccessOAuth2ProcessingFilter.class);

    private final UserService userService;

    OnSuccessOAuth2ProcessingFilter(String defaultFilterProcessesUrl, UserService userService) {
        super(defaultFilterProcessesUrl);
        this.userService = userService;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        if (authResult == null || !authResult.isAuthenticated()) {
            logger.info("Authentication failed");
            return;
        }
        logger.info("User has passed the authentication process, calling the service to create or find the user");
        userService.login(authResult, request.getRequestURI());
    }

    private Map convertAuthDetailsToMap(Authentication authentication) {
        OAuth2Authentication auth = (OAuth2Authentication) authentication;
        ObjectMapper mapper = new ObjectMapper();
        Map map =mapper.convertValue(auth.getUserAuthentication().getDetails(), Map.class);
        logger.info("{}", map);
        return map;
    }
}
