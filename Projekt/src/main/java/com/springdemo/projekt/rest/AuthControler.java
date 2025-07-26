package com.springdemo.projekt.rest;

import com.springdemo.projekt.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControler {

    private static final Logger LOG = LoggerFactory.getLogger(AuthControler.class);

    private final TokenService tokenService;

    public AuthControler(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user" + authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted" + token);
        return token;
    }
}
