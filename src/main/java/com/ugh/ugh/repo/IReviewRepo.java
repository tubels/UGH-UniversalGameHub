package com.ugh.ugh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.User;

public interface IReviewRepo extends CrudRepository<Review, Long> {

	boolean existsByUserAndGame(User user, Game game);

	boolean existsByRating(int rating);

	ArrayList<Review> findAllByRating(int rating);
}
