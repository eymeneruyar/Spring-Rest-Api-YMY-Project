package com.ymy.company.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "City",schema = "ymy")
public class Cities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sehir_title")
    private String name;

    @Column(name = "sehir_key")
    private Long cityKey;

}
