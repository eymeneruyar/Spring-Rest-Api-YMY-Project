package com.ymy.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "Users",schema = "ymy")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100)
    @NotNull(message = "Kullanıcı ismi boş olamaz!")
    @NotEmpty(message = "Kullanıcı ismi boş olamaz!")
    @Comment(value = "Kullanıcı ismi")
    private String name;

    @Column(length = 50)
    @NotNull(message = "Kullanıcı soyismi boş olamaz!")
    @NotEmpty(message = "Kullanıcı soyismi boş olamaz!")
    @Comment(value = "Kullanıcı soyismi")
    private String surname;

    @Column(length = 50)
    @NotNull(message = "Kullanıcı soyismi boş olamaz!")
    @NotEmpty(message = "Kullanıcı soyismi boş olamaz!")
    @Comment(value = "Kullanıcı adı")
    private String username;

    @Column(unique = true,length = 100)
    @NotNull(message = "Kullanıcı email boş olamaz!")
    @NotEmpty(message = "Kullanıcı email boş olamaz!")
    @Comment(value = "Kullanıcı email")
    private String email;

    @Column(unique = true,length = 75)
    @NotNull(message = "Kullanıcı firması boş olamaz!")
    @NotEmpty(message = "Kullanıcı firması boş olamaz!")
    @Comment(value = "Kullanıcı firması")
    private String companyName;

    private String password;

    private String profileImage;

    private boolean enabled = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(	name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String name,String surname,String username,String email,String companyName,String password,Set<Role> roles){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.companyName = companyName;
        this.password = password;
        this.roles = roles;
    }

}
