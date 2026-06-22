package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.User;

public interface IReviewRepo extends CrudRepository<Review, Long> {

	boolean existsByUserAngGame(User user, Game game);

}
