package com.sorsix.eventagregator.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectURLAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static Logger logger = LoggerFactory.getLogger(RedirectURLAuthenticationSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Handling the request");
        String redirectURL = "http://localhost:8080/users";
        if (response.isCommitted()) {
            logger.debug("Response has been commited");
        } else {
            redirectStrategy.sendRedirect(request, response, redirectURL);
        }
    }
}
