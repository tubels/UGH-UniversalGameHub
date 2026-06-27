package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.User;
import com.ugh.ugh.model.UserGame;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IUserGameRepo;
import com.ugh.ugh.repo.IUserRepo;
import com.ugh.ugh.service.IUsergameCRUDService;

@Service
public class UsergameCRUDServiceImpl implements IUsergameCRUDService {

	private final IUserGameRepo userGameRepo;
	private final IUserRepo userRepo;
	private final IGameRepo gameRepo;

	UsergameCRUDServiceImpl(IUserGameRepo userGameRepo, IUserRepo userRepo, IGameRepo gameRepo) {
		this.userGameRepo = userGameRepo;
		this.userRepo = userRepo;
		this.gameRepo = gameRepo;
	}

	@Override
	public void create(GameStatus gameStatus, long userId, long gameId) throws Exception {
		if (gameStatus == null || userId <= 0 || gameId <= 0)
			throw new Exception("One or more input fields are incorrect");

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new Exception("User not found"));
		Game game = gameRepo.findById(gameId)
				.orElseThrow(() -> new Exception("Game not found"));

		if (userGameRepo.existsByUserAndGame(user, game))
			throw new Exception("This game already exists in user's library");

		UserGame newUserGame = new UserGame(gameStatus, user, game);
		userGameRepo.save(newUserGame);
	}

	@Override
	public ArrayList<UserGame> retrieveAll() throws Exception {
		if (userGameRepo.count() == 0)
			throw new Exception("There are no UserGames to retrieve");
		ArrayList<UserGame> allUserGames = (ArrayList<UserGame>) userGameRepo.findAll();
		return allUserGames;
	}

	@Override
	public UserGame retrieveById(long id) throws Exception {
		if (id <= 0)
			throw new Exception("Id cannot be negative or 0");
		if (!userGameRepo.existsById(id))
			throw new Exception("No such UserGame exists with the id: " + id);
		UserGame userGameFromDB = userGameRepo.findById(id).get();
		return userGameFromDB;
	}

	@Override
	public void updateById(long id, GameStatus gameStatus, long userId, long gameId) throws Exception {
		UserGame userGameFromDB = retrieveById(id);
		if (gameStatus == null || userId <= 0 || gameId <= 0)
			throw new Exception("One or more input fields are incorrect");

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new Exception("User not found"));
		Game game = gameRepo.findById(gameId)
				.orElseThrow(() -> new Exception("Game not found"));

		if (!userGameFromDB.getGameStatus().equals(gameStatus))
			userGameFromDB.setGameStatus(gameStatus);
		if (!userGameFromDB.getUser().equals(user))
			userGameFromDB.setUser(user);
		if (!userGameFromDB.getGame().equals(game))
			userGameFromDB.setGame(game);
		userGameRepo.save(userGameFromDB);
	}

	@Override
	public void deleteById(long id) throws Exception {
		UserGame userGameFromDB = retrieveById(id);
		userGameRepo.delete(userGameFromDB);
	}
}
