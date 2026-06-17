package com.ugh.ugh.service;

import com.ugh.ugh.model.Publisher;

public interface IPublisherCRUDService extends ICRUDServiceBase<Publisher> {
	
	public abstract void create(String name) throws Exception;
	public abstract void updateById(long id, String name) throws Exception;
}
