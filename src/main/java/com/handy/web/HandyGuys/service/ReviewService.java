package com.handy.web.HandyGuys.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.handy.web.HandyGuys.Models.Review;
import com.handy.web.HandyGuys.repository.IReviewRepository;

public class ReviewService {

    @Autowired
    private IReviewRepository repository;

    public String saveReview(Review review) {
        repository.save(review);
        return "Review saved successfully";
    }

    public String updateReview(Review review) {
        Optional<Review> existingReview = repository.findById(review.getId());
        if (existingReview.isPresent()) {
            Review updatedReview = existingReview.get();
            updatedReview.setRating(review.getRating());
            updatedReview.setReview(review.getReview());
            repository.save(updatedReview);
            return "Review updated successfully";
        } else {
            return "Review not found";
        }
    }

    public Review getReview(UUID id) {
        Optional<Review> review = repository.findById(id);

        if (review.isPresent()) {
            return review.get();
        } else {
            return null;
        }
    }

}
