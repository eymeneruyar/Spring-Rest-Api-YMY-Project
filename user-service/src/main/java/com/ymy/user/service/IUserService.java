package com.ymy.user.service;

import com.ymy.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    String signUpUser(User user);
    int enableUser(String email);
    public ResponseEntity<?> getDetailOfUserByUsername(String username);

}
