package com.sorsix.eventagregator.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class AuthController {


    //Get info about the user
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }


}
