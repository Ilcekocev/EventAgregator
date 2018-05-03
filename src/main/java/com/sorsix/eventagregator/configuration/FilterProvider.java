package com.sorsix.eventagregator.configuration;

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

    public FilterProvider(OAuth2ClientContext oauth2ClientContext) {
        this.oauth2ClientContext = oauth2ClientContext;
    }

    public OAuth2ClientAuthenticationProcessingFilter createFilter(String processURL,
                                                                   AuthorizationCodeResourceDetails client,
                                                                   ResourceServerProperties resource) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(processURL);
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(client, oauth2ClientContext);
        filter.setRestTemplate(restTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), client.getClientId());
        tokenServices.setRestTemplate(restTemplate);
        filter.setTokenServices(tokenServices);
        return filter;
    }
}
