package com.ugh.ugh.service;

import com.ugh.ugh.model.User;

public interface IUserCRUDService extends ICRUDServiceBase<User>{
	public abstract void create(String username, String email, String password) throws Exception;
	public abstract void updateById(long id, String username, String email) throws Exception;
}
