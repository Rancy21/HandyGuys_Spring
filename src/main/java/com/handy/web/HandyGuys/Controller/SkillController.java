package com.handy.web.HandyGuys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.Skill;
import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.SkillService;
import com.handy.web.HandyGuys.service.UserService;

@RestController
@RequestMapping(value = "/skill")
public class SkillController {
    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveSkill", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody Skill skill, @RequestParam(required = true) String email) {
        if (skill.getId() == null || skill.getCategory().toString().trim().isEmpty()
                || skill.getDescription().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        User handy = userService.getUser(email);
        if (handy == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        skill.setHandyGuy(handy);

        String saveSkill = skillService.saveSkill(skill);

        if (saveSkill.equalsIgnoreCase("User already exists")) {
            return new ResponseEntity<>("User with that email already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("skill successfully saved", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateSkill", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateSkill(@RequestBody Skill skil) {
        if (skil.getId() == null || skil.getCategory().toString().trim().isEmpty()
                || skil.getDescription().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        String updateSkill = skillService.updateSkill(skil);
        if (updateSkill.equalsIgnoreCase("Skill not found")) {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Skill successfully updated", HttpStatus.OK);
        }
    }

    @PostMapping(value = "/deleteSkill", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSkill(@RequestBody Skill skil) {
        if (skil.getId() == null || skil.getCategory().toString().trim().isEmpty()
                || skil.getDescription().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        String deleteSkill = skillService.updateSkill(skil);
        if (deleteSkill.equalsIgnoreCase("Skill not found")) {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>("Skill successfully deleted", HttpStatus.OK);
        }
    }

}