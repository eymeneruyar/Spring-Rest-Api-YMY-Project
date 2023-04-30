package com.ymy.user.entity;

import com.ymy.user.utility.ERole;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(schema = "ymy")
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole name;

}
