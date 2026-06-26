package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ugh.ugh.model.User;
import com.ugh.ugh.repo.IUserRepo;
import com.ugh.ugh.service.IUserCRUDService;

@Service
public class UserCRUDServiceImpl implements IUserCRUDService, UserDetailsService {

	@Autowired
	private IUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

	private final IUserRepo IuserRepo;

    public UserCRUDServiceImpl(IUserRepo IuserRepo) {
        this.IuserRepo = IuserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return IuserRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

	@Override
	public void create(String username, String email, String password) throws Exception {
		if (username == null || username.isEmpty()
				|| email == null || email.isEmpty()) throw new Exception("Username and email cannot be empty");
		if (userRepo.existsByUsername(username)) throw new Exception("User with this username already exists");
		if (userRepo.existsByEmail(email)) throw new Exception("User with this email already exists");
		User newUser = new User(username, email, passwordEncoder.encode(password));
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
