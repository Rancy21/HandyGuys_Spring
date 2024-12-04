package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.UUID;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Skill;

@Repository
public interface ISkillRepository extends JpaRepository<Skill, UUID> {

    List<Skill> findByCategory(Category category);
}
