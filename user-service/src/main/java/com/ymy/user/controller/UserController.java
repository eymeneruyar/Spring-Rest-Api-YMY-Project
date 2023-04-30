package com.ymy.user.controller;

import com.ymy.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping("/detailOfUserByUsername/{username}")
    public ResponseEntity<?> getDetailOfUserByUsername(@PathVariable String username){
        return userService.getDetailOfUserByUsername(username);
    }

}
