package com.handy.web.HandyGuys.Controller;

import java.util.ArrayList;
import java.util.List;
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
import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.RatingService;
import com.handy.web.HandyGuys.service.ReviewService;
import com.handy.web.HandyGuys.service.SkillService;
import com.handy.web.HandyGuys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Autowired
    private UserService userService;

    @Autowired
    private SkillService skillService;
    @Autowired
    private RatingService ratingService;

    @PostMapping(value = "/saveReview", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody Review review, @RequestParam String skillId,
            @RequestParam String email) {
        Skill skill = skillService.getSkill(UUID.fromString(skillId));
        User user = userService.getUser(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        if (skill == null) {
            return new ResponseEntity<>("Skill not found", HttpStatus.NOT_FOUND);
        }

        Review existingReview = service.getReview(user, skill);
        Rating rating = new Rating();
        if (existingReview == null) {
            review.setUser(user);
            review.setSkill(skill);

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

        if (skill.getRating() == null) {
            return new ResponseEntity<>("Rating for this skill not found", HttpStatus.NOT_FOUND);
        } else {
            rating = skill.getRating();
            rating.setCumulatedRatings(rating.getCumulatedRatings() - existingReview.getRating());
            rating.setCumulatedRatings(rating.getCumulatedRatings() + review.getRating());
            rating.setAvgRating((float) (rating.getCumulatedRatings() / rating.getNumberOfRatings()));
            ratingService.updateRating(rating);
        }

        existingReview.setDate(review.getDate());
        existingReview.setRating(review.getRating());
        existingReview.setReview(review.getReview());
        return new ResponseEntity<>(service.updateReview(existingReview), HttpStatus.OK);

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

    @GetMapping(value = "/getReview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStudent(@RequestParam String email, @RequestParam String id) {

        User user = userService.getUser(email);
        Skill skill = skillService.getSkill(UUID.fromString(id));

        Review existingReview = service.getReview(user, skill);
        if (existingReview == null) {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(existingReview, HttpStatus.OK);
    }

    @GetMapping(value = "/getReviewsList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getList(@RequestParam String id) {
        Skill skill = skillService.getSkill(UUID.fromString(id));

        List<Review> reviews = service.getReviews(skill);

        if (reviews.isEmpty() || reviews == null) {
            return new ResponseEntity<>("No reviews found for this skill", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping(value = "/getTopRating", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTopRating() {

        List<Rating> ratings = ratingService.getTop3Rating();
        List<Skill> topSkills = new ArrayList<Skill>();
        for (Rating rating : ratings) {
            Skill skill = skillService.getSkill(rating.getSkill().getId());
            topSkills.add(skill);
        }

        if (ratings.isEmpty() || ratings == null) {
            return new ResponseEntity<>("No Featured helpers yet", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(topSkills, HttpStatus.OK);
    }

}
