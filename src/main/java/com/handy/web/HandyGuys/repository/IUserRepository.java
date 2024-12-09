package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String email);

    List<User> findAllByOrderBySignUpDateDesc();

    List<User> findAllByIsHandyTrue();
}