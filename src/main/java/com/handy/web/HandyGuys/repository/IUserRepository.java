package com.handy.web.HandyGuys.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handy.web.HandyGuys.Models.User;

public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);
}