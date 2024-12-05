package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Review;

@Repository
public interface IReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findBySkillId(UUID skillId);
}
