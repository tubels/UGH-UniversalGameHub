package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Review;
import com.ugh.ugh.model.User;
import com.ugh.ugh.repo.IReviewRepo;
import com.ugh.ugh.service.IReviewCRUDService;

@Service
public class ReviewCRUDServiceImpl implements IReviewCRUDService {
	
	@Autowired
	private IReviewRepo reviewRepo;
	
	@Override
	public void create(String title, int rating, String description, User user, Game game) throws Exception {
		if (reviewRepo.existsByUserAngGame(user, game)) throw new Exception("You cannot make a new review on a game you've already reviewed");
		if (title == null || title.isEmpty() 
				|| rating < 1 || rating > 5
				|| description == null || description.isEmpty()
				|| user == null || game == null) throw new Exception ("One or more of the input fields are incorrect or empty");
		Review newRev = new Review(title, rating, description, user, game);
		reviewRepo.save(newRev);
	}
	
	@Override
	public ArrayList<Review> retrieveAll() throws Exception {
		if (reviewRepo.count() == 0) throw new Exception("There are no reviews to retrieve");
		ArrayList<Review> allRev = (ArrayList<Review>) reviewRepo.findAll();
		return allRev;
	}
	
	@Override
	public Review retrieveById(long id) throws Exception {
		if (id <= 0) throw new Exception("Id cannot be negative or 0");
		if (!reviewRepo.existsById(id)) throw new Exception("There are no reviews with the id: " + id);
		
		Review reviewFromDB = reviewRepo.findById(id).get();
		return reviewFromDB;
	}
	
	@Override
	public void updateById(long id, String title, int rating, String description, User user, Game game) throws Exception {
		Review reviewFromDB = retrieveById(id);
		
		if (title == null || title.isEmpty() 
				|| rating < 1 || rating > 5
				|| description == null || description.isEmpty()
				|| user == null || game == null) throw new Exception ("One or more of the input fields are incorrect or empty");
		if (!reviewFromDB.getTitle().equals(title)) reviewFromDB.setTitle(title);
		if (reviewFromDB.getRating() != rating) reviewFromDB.setRating(rating);
		if (!reviewFromDB.getDescription().equals(description)) reviewFromDB.setDescription(description);
		if (!reviewFromDB.getUser().equals(user)) reviewFromDB.setUser(user);
		if (!reviewFromDB.getGame().equals(game)) reviewFromDB.setGame(game);
		reviewRepo.save(reviewFromDB);
	}
	
	@Override
	public void deleteById(long id) throws Exception {
		Review reviewFromDB = retrieveById(id);
		reviewRepo.delete(reviewFromDB);
	}
}
