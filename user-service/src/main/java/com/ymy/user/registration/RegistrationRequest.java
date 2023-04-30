package com.ymy.user.registration;

import com.ymy.user.entity.Role;
import lombok.Data;

import java.util.Set;

@Data
public class RegistrationRequest {
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private String companyName;
    private Set<Role> roles;
}
