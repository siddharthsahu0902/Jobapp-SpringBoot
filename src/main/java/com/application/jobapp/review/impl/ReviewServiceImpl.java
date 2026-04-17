package com.application.jobapp.review.impl;

import com.application.jobapp.company.Company;
import com.application.jobapp.company.CompanyService;
import com.application.jobapp.review.Review;
import com.application.jobapp.review.ReviewRepository;
import com.application.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Review review, Long companyId) {
        Company company = companyService.getCompany(companyId);
        if(company!=null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId, Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        for(Review review : reviews) {
            if(review.getId().equals(reviewId)) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean updateReview(Review review, Long reviewId, Long companyId) {
        Review existingReview = getReview(reviewId, companyId);
        if(existingReview!=null) {
            existingReview.setTitle(review.getTitle());
            existingReview.setDescription(review.getDescription());
            existingReview.setRating(review.getRating());
            reviewRepository.save(existingReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId, Long companyId) {
        if(companyService.getCompany(companyId)!=null && reviewRepository.existsById(reviewId)) {
            Review review  = reviewRepository.findById(reviewId).get();
            Company company  = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
