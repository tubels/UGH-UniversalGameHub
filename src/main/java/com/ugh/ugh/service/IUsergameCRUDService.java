package com.ugh.ugh.service;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.User;
import com.ugh.ugh.model.UserGame;

public interface IUsergameCRUDService extends ICRUDServiceBase<UserGame> {

	public abstract void create(GameStatus gameStatus, User user, Game game) throws Exception;
	public abstract void updateById(long id, GameStatus gameStatus, User user, Game game) throws Exception;
}
