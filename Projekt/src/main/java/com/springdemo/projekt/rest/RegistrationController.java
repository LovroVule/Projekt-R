package com.springdemo.projekt.rest;

import com.springdemo.projekt.dao.UserRepository;
import com.springdemo.projekt.domain.LoginForm;
import com.springdemo.projekt.domain.MyUser;
import com.springdemo.projekt.service.impl.MyUserDetailService;
import com.springdemo.projekt.webtoken.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/register/user")
    public MyUser createUser(@RequestBody MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginForm.username(), loginForm.password()
                        ));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailService.loadUserByUsername(loginForm.username()));
        }
        else{
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
