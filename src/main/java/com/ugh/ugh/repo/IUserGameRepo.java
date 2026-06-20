package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.User;
import com.ugh.ugh.model.UserGame;

public interface IUserGameRepo extends CrudRepository<UserGame, Long> {

	boolean existsByUserAndGame(User user, Game game);

}
