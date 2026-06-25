package com.ugh.ugh.service;

import java.time.LocalDate;
import java.util.Collection;

import com.ugh.ugh.model.Developer;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Genre;
import com.ugh.ugh.model.Publisher;

public interface IGameCRUDService extends ICRUDServiceBase<Game> {
	
	public abstract void create(String title, float price, String description, LocalDate releaseDate, 
								long developerId, long publisherId, Long[] genreIds) throws Exception;
	public abstract void updateById(long id, String title, float price, String description, LocalDate releaseDate, 
								long developerId, long publisherId, Long[] genreIds) throws Exception;
}
