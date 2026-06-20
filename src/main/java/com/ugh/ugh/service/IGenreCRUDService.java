package com.ugh.ugh.service;

import com.ugh.ugh.model.Genre;

public interface IGenreCRUDService extends ICRUDServiceBase<Genre> {

	public abstract void create(String name) throws Exception;
	public abstract void updateById(long id, String name) throws Exception;
}
