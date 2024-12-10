package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Rating;
import com.handy.web.HandyGuys.Models.Skill;

// import java.util.List;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, UUID> {
    Optional<Rating> findBySkill(Skill skill);

    List<Rating> findTop3ByOrderByAvgRatingDesc();
}
