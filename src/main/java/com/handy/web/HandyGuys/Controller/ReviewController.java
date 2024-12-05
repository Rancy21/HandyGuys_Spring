package com.handy.web.HandyGuys.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.Rating;
import com.handy.web.HandyGuys.Models.Review;
import com.handy.web.HandyGuys.Models.Skill;
import com.handy.web.HandyGuys.service.RatingService;
import com.handy.web.HandyGuys.service.ReviewService;
import com.handy.web.HandyGuys.service.SkillService;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Autowired
    private SkillService skillService;
    @Autowired
    private RatingService ratingService;

    @PostMapping(value = "/saveReview", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody Review review, @RequestParam String skillId) {
        Skill skill = skillService.getSkill(UUID.fromString(skillId));
        if (skill == null) {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }

        Rating rating = new Rating();
        if (skill.getRating() == null) {
            rating.setSkill(skill);
            rating.setCumulatedRatings(rating.getCumulatedRatings() + review.getRating());
            rating.setNumberOfRatings(rating.getNumberOfRatings() + 1);
            rating.setAvgRating((float) (rating.getCumulatedRatings() / rating.getNumberOfRatings()));
            ratingService.saveRating(rating);
        } else {
            rating = skill.getRating();
            rating.setCumulatedRatings(rating.getCumulatedRatings() + review.getRating());
            rating.setNumberOfRatings(rating.getNumberOfRatings() + 1);
            rating.setAvgRating((float) (rating.getCumulatedRatings() / rating.getNumberOfRatings()));
            ratingService.saveRating(rating);
        }

        return new ResponseEntity<>(service.saveReview(review), HttpStatus.OK);
    }

    @PostMapping(value = "/updateReview", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestBody Review review, @RequestParam String skillId) {
        Skill skill = skillService.getSkill(UUID.fromString(skillId));
        if (skill == null) {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }

        Review existingReview = service.getReview(review.getId());
        Rating rating = new Rating();
        review.setSkill(skill);
        if (skill.getRating() == null) {
            return new ResponseEntity<>("Rating for this skill not found", HttpStatus.NOT_FOUND);
        } else {
            rating = skill.getRating();
            rating.setCumulatedRatings(rating.getCumulatedRatings() - existingReview.getRating());
            rating.setCumulatedRatings(rating.getCumulatedRatings() + review.getRating());
            rating.setAvgRating((float) (rating.getCumulatedRatings() / rating.getNumberOfRatings()));
            ratingService.saveRating(rating);
        }

        return new ResponseEntity<>(service.updateReview(review), HttpStatus.OK);
    }

}
