package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Game;

public interface IGameRepo extends CrudRepository<Game, Long> {
	
	boolean existsByTitle(String title);
}
