package com.ugh.ugh.service;

import java.time.LocalDate;

import com.ugh.ugh.model.Game;

public interface IGameCRUDService extends ICRUDServiceBase<Game> {

	public abstract void create(String title, float price, String description, LocalDate releaseDate,
			long developerId, long publisherId, Long[] genreIds) throws Exception;

	public abstract void updateById(long id, String title, float price, String description, LocalDate releaseDate,
			long developerId, long publisherId, Long[] genreIds) throws Exception;
}
