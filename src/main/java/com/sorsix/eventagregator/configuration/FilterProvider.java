package com.sorsix.eventagregator.configuration;

import com.sorsix.eventagregator.service.UserService;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.stereotype.Component;

@Component
public class FilterProvider {

    private final OAuth2ClientContext oauth2ClientContext;
    private final UserService userService;

    public FilterProvider(OAuth2ClientContext oauth2ClientContext, UserService userService) {
        this.oauth2ClientContext = oauth2ClientContext;
        this.userService = userService;
    }

    OAuth2ClientAuthenticationProcessingFilter createFilter(String processURL,
                                                            AuthorizationCodeResourceDetails client,
                                                            ResourceServerProperties resource) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OnSuccessOAuth2ProcessingFilter(processURL, userService);
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client, oauth2ClientContext);
        filter.setRestTemplate(restTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), client.getClientId());
        tokenServices.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);
        return filter;
    }
}
