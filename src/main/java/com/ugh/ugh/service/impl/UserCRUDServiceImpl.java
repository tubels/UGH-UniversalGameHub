package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugh.ugh.model.Publisher;
import com.ugh.ugh.model.User;
import com.ugh.ugh.repo.IUserRepo;
import com.ugh.ugh.service.IUserCRUDService;

@Service
public class UserCRUDServiceImpl implements IUserCRUDService {
	
	@Autowired
	private IUserRepo userRepo;

	@Override
	public void create(String username, String email) throws Exception {
		if (username == null || username.isEmpty()
				|| email == null || email.isEmpty()) throw new Exception("Username and email cannot be empty");
		if (userRepo.existsByUsername(username)) throw new Exception("User with this username already exists");
		if (userRepo.existsByEmail(email)) throw new Exception("User with this email already exists");
		User newUser = new User(username, email);
		userRepo.save(newUser);
	}
	
	@Override
	public ArrayList<User> retrieveAll() throws Exception {
		if (userRepo.count() == 0) throw new Exception("There are no Users to retrieve");
		ArrayList<User> allUsers = (ArrayList<User>) userRepo.findAll();
		return allUsers;
	}

	@Override
	public User retrieveById(long id) throws Exception {
		if (id <= 0) throw new Exception("Id cannot be negative or 0");
		if (!userRepo.existsById(id)) throw new Exception("There are no Users with the id: " + id);
		User userFromDB = userRepo.findById(id).get();
		return userFromDB;
	}

	@Override
	public void updateById(long id, String username, String email) throws Exception {
		User userFromDB = retrieveById(id);
		
		if (username == null || username.isEmpty()
				|| email == null || email.isEmpty()) throw new Exception("Username and email cannot be empty");

		if (!userFromDB.getUsername().equals(username)) userFromDB.setUsername(username);
		if (!userFromDB.getEmail().equals(email)) userFromDB.setEmail(email);
		userRepo.save(userFromDB);
	}
	
	@Override
	public void deleteById(long id) throws Exception {
		User userFromDB = retrieveById(id);
		userRepo.delete(userFromDB);
	}
}
