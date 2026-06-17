package com.ugh.ugh.service;

import com.ugh.ugh.model.Developer;

public interface IDeveloperCRUDService extends ICRUDServiceBase<Developer> {
	
	public abstract void create(String name) throws Exception;
	public abstract void updateById(long id, String name) throws Exception;
}
