package com.application.jobapp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable("companyId") Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable("reviewId") Long reviewId, @PathVariable Long companyId) {
        Review review = reviewService.getReview(reviewId, companyId);
        if(review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@RequestBody Review review, @PathVariable("companyId") Long companyId) {
        try{
            boolean isAdded = reviewService.addReview(review, companyId);
            if(isAdded) return new ResponseEntity<>("Review added successfully.", HttpStatus.OK);
            return new ResponseEntity<>("Failed to add the review, check if company exists..", HttpStatus.BAD_REQUEST);
        }
        catch(Exception e){
            return new ResponseEntity<>("Could not add the review.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@RequestBody Review review, @PathVariable("reviewId") Long reviewId, @PathVariable("companyId") Long companyId) {
        boolean isUpdated = reviewService.updateReview(review, reviewId, companyId);
        if(isUpdated) return new ResponseEntity<>("Review updated successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Failed to update the review, check if company exists..", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId, @PathVariable Long companyId) {
        boolean isDeleted = reviewService.deleteReview(reviewId, companyId);
        if(isDeleted) return new ResponseEntity<>("Review deleted successfully.", HttpStatus.OK);
        return new ResponseEntity<>("Review deletion Failed.", HttpStatus.BAD_REQUEST);
    }

}
