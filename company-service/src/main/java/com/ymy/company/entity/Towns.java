package com.ymy.company.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Town",schema = "ymy")
public class Towns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ilce_title")
    private String name;

    @Column(name = "ilce_key")
    private Long townKey;

    @Column(name = "ilce_sehirkey")
    private Long townCityKey;

}
