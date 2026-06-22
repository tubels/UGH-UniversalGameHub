package com.ugh.ugh.service;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.User;

public interface IReviewCRUDService extends ICRUDServiceBase<Review> {
	
	public abstract void create(String title, int rating, String description, User user, Game game) throws Exception;
	public abstract void updateById(long id, String title, int rating, String description, User user, Game game) throws Exception;
}
