package com.springdemo.projekt.service.impl;


import com.springdemo.projekt.dao.UserRepository;
import com.springdemo.projekt.domain.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(userObj.getRole())
                    .build();
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(MyUser user) {
        if(user.getRole() == null){
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }
}
