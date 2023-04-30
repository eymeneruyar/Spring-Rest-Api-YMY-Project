package com.ymy.user.repository;

import com.ymy.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled = true WHERE u.email = ?1")
    int enableUser(String email);

}
