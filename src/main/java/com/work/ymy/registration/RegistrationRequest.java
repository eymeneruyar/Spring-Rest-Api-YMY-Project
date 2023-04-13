package com.work.ymy.registration;

import com.work.ymy.user.Role;
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
