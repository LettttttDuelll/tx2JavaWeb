package com.example.laptopshop.repository;

import com.example.laptopshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername (String username);
    Boolean existsByUsername (String username);
}
