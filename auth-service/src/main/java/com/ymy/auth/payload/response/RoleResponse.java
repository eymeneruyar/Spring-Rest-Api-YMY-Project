package com.ymy.auth.payload.response;

import com.ymy.auth.utility.ERole;
import lombok.Data;

@Data
public class RoleResponse {
    private Long id;
    private ERole name;
}
