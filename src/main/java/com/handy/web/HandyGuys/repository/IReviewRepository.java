package com.handy.web.HandyGuys.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handy.web.HandyGuys.Models.Review;

public interface IReviewRepository extends JpaRepository<Review, UUID> {
}
