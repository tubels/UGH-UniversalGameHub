package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Review;

public interface IReviewRepo extends CrudRepository<Review, Long> {

}
