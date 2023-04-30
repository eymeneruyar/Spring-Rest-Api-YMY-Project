package com.ymy.auth.service;

import com.ymy.auth.feign.UserFeignClient;
import com.ymy.auth.payload.response.UserResponse;
import com.ymy.auth.prop.UserProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserFeignClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        Optional<UserResponse> userOptional = userFeignClient.getDetailOfUserByUsername(username);
        if(userOptional.isPresent()){
            UserResponse user = userOptional.get();
            userDetails = UserProperties.build(user);
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userDetails;
    }

}
