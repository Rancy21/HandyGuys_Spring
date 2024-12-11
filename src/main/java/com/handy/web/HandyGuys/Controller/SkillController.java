package com.handy.web.HandyGuys.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.ECategory;
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
        if (skill.getCategory().toString().trim().isEmpty()
                || skill.getDescription().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        User handy = userService.getUser(email);
        if (handy == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        skill.setHandyGuy(handy);

        String saveSkill = skillService.saveSkill(skill);

        if (saveSkill.equalsIgnoreCase("skill saved successfully")) {
            return new ResponseEntity<>("Skill saved successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("An error occured", HttpStatus.INTERNAL_SERVER_ERROR);
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

    @GetMapping(value = "/getSkillperCategory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSkillsByCategory(@RequestParam(required = true) String category) {

        try {

            ECategory cat = null;
            for (ECategory ecategory : ECategory.values()) {
                if (ecategory.getDisplayName().equalsIgnoreCase(category)) {
                    cat = ecategory;
                }
            }
            List<Skill> skills = skillService.getSkillsPerCategory(cat);
            if (skills.isEmpty()) {
                return new ResponseEntity<>("No skills found for this category", HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(skills, HttpStatus.OK);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Invalid category", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getAllSkills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSkills() {
        List<Skill> skills = skillService.getAllSkills();
        if (skills.isEmpty()) {
            return new ResponseEntity<>("No skills found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(skills, HttpStatus.OK);
        }
    }
    @GetMapping(value = "/globalSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> globalSearch(@RequestParam(required = true) String value) {
        List<Skill> skills = skillService.getAllSkills();
        List<Skill> result = new ArrayList<>();
        String fullName = "";
        value = value.trim();
        value = value.toLowerCase();
        
        if (skills.isEmpty()) {
            return new ResponseEntity<>("No skills found", HttpStatus.NOT_FOUND);
        } else {
            for (Skill skill : skills) {
                fullName = skill.getHandyGuy().getFirstName().toLowerCase() + " " + skill.getHandyGuy().getLastName().toLowerCase();
                if(skill.getDescription().toLowerCase().contains(value) || fullName.contains(value) 
                || skill.getCategory().getDisplayName().toLowerCase().contains(value)){
                    result.add(skill);
                }
            }
            if(result.isEmpty()){
                return new ResponseEntity<>("No skills found with the given search value", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getCategories", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCategories() {
        List<String> categories = Arrays.stream(ECategory.values())
                .map(ECategory::getDisplayName)
                .toList();

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(value = "/getHandySkills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHandySkills(@RequestParam String email) {
        User handy = userService.getUser(email);
        if (handy == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        List<Skill> skills = skillService.getHandySkills(handy);
        return new ResponseEntity<>(skills, HttpStatus.OK);
    }

    @GetMapping(value = "/getSkill", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSkill(@RequestParam(required = true) String id) {
        Skill skill = skillService.getSkill(UUID.fromString(id));
        if (skill == null) {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(skill, HttpStatus.OK);
        }
    }

}
