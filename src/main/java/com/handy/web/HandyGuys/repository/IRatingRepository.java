package com.handy.web.HandyGuys.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Rating;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, UUID> {
}
