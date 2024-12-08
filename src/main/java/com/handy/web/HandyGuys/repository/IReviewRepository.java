package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Review;
import com.handy.web.HandyGuys.Models.Skill;
import com.handy.web.HandyGuys.Models.User;

@Repository
public interface IReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllBySkill(Skill skill);

    Optional<Review> findByUserAndSkill(User user, Skill skill);
}
