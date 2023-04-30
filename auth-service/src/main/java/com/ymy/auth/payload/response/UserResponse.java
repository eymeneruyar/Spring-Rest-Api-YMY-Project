package com.ymy.auth.payload.response;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String companyName;
    private String password;
    private String profileImage;
    private boolean enabled = false;
    private Set<RoleResponse> roles = new HashSet<>();
}
