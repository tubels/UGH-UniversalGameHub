package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.UserGame;

public interface IUserGameRepo extends CrudRepository<UserGame, Long> {

}
