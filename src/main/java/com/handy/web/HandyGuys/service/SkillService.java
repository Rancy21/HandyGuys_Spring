package com.handy.web.HandyGuys.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.handy.web.HandyGuys.Models.Skill;
import com.handy.web.HandyGuys.repository.ISkillRepository;

public class SkillService {

    @Autowired
    private ISkillRepository repo;

    public String saveSkill(Skill skill) {
        repo.save(skill);
        return "skill saved successfully";
    }

    public String updateSkill(Skill skill) {
        Optional<Skill> existingSkill = repo.findById(skill.getId());
        if (existingSkill.isPresent()) {
            Skill updatedSkill = existingSkill.get();
            updatedSkill.setCategory(skill.getCategory());
            updatedSkill.setDescription(skill.getDescription());
            repo.save(updatedSkill);
            return "skill updated successfully";
        } else {
            return "Skill not found";
        }
    }

    public String deleteSkill(Skill skill) {
        Optional<Skill> existingSkill = repo.findById(skill.getId());
        if (existingSkill.isPresent()) {
            Skill deletedSkill = existingSkill.get();
            repo.delete(deletedSkill);
            return "skill updated successfully";
        } else {
            return "Skill not found";
        }
    }

    public Skill getSkill(UUID id) {
        Optional<Skill> skill = repo.findById(id);
        if (skill.isPresent()) {
            return skill.get();
        } else {
            return null;
        }
    }
}