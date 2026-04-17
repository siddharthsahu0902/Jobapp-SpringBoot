package com.application.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Review review, Long companyId);
    Review getReview(Long reviewId, Long companyId);
    boolean updateReview(Review review, Long reviewId, Long companyId);
    boolean deleteReview(Long reviewId, Long companyId);
}
