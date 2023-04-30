package com.ymy.user.service;

import com.ymy.user.entity.User;
import com.ymy.user.prop.UserProperties;
import com.ymy.user.registration.token.ConfirmationToken;
import com.ymy.user.registration.token.ConfirmationTokenService;
import com.ymy.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            userDetails = UserProperties.build(user);
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return userDetails;
    }

    @Override
    public String signUpUser(User user) {

        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException("Email or username already taken!");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15), user);
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    @Override
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    @Override
    public ResponseEntity<?> getDetailOfUserByUsername(String username) {
        return ResponseEntity.ok(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)));
    }

}
