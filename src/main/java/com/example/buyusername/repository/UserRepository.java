package com.example.buyusername.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.buyusername.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
