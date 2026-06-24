package com.ugh.ugh.service;

import com.ugh.ugh.model.Review;

public interface IReviewCRUDService extends ICRUDServiceBase<Review> {
	
	public abstract void create(String title, int rating, String description, long userId, Long gameId) throws Exception;
	public abstract void updateById(long id, String title, int rating, String description, long userId, Long gameId) throws Exception;
}
