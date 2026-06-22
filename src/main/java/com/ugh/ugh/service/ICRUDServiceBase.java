package com.ugh.ugh.service;

import java.util.ArrayList;

public interface ICRUDServiceBase<Ttype> {
	
	public abstract ArrayList<Ttype> retrieveAll() throws Exception;
	public abstract Ttype retrieveById(long id) throws Exception;
	public abstract void deleteById(long id) throws Exception;
}
