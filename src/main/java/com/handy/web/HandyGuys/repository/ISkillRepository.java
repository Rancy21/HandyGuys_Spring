package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.UUID;
import com.handy.web.HandyGuys.Models.ECategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Skill;
import com.handy.web.HandyGuys.Models.User;

@Repository
public interface ISkillRepository extends JpaRepository<Skill, UUID> {

    List<Skill> findByCategory(ECategory category);

    List<Skill> findAllByHandyGuy(User handyGuy);
}
