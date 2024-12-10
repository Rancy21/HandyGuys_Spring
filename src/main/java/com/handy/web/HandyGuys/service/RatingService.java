package com.handy.web.HandyGuys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.Rating;
import com.handy.web.HandyGuys.repository.IRatingRepository;

@Service
public class RatingService {

    @Autowired
    IRatingRepository repo;

    public String saveRating(Rating rating) {
        repo.save(rating);
        return "Rating saved successfully";
    }

    public String updateRating(Rating rating) {
        Optional<Rating> oldRating = repo.findById(rating.getId());
        if (oldRating.isPresent()) {
            oldRating.get().setNumberOfRatings(rating.getNumberOfRatings());
            oldRating.get().setCumulatedRatings(rating.getCumulatedRatings());
            oldRating.get().setAvgRating(rating.getAvgRating());
            repo.save(oldRating.get());
            return "Rating updated successfully";
        }
        return "Rating not found";
    }

    public List<Rating> getTop3Rating() {
        return repo.findTop3ByOrderByAvgRatingDesc();
    }
}
