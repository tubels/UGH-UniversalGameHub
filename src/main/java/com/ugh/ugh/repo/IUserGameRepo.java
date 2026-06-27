package com.ugh.ugh.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.User;
import com.ugh.ugh.model.UserGame;

public interface IUserGameRepo extends CrudRepository<UserGame, Long> {

	boolean existsByUserAndGame(User user, Game game);

	boolean existsByGameStatus(GameStatus gameStatus);

	ArrayList<UserGame> findAllByGameStatus(GameStatus gameStatus);
}
