package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.ugh.ugh.model.Publisher;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.service.IPublisherCRUDService;

public class PublisherCRUDSerivceImpl implements IPublisherCRUDService {
	
	@Autowired
	private IPublisherRepo pubRepo;
	
	@Override
	public void create(String name) throws Exception {
		if (name == null || name.isEmpty()) throw new Exception("The name cannot be empty");
		if(pubRepo.existsByName(name)) throw new Exception("Publisher with this name already exists");
		Publisher newPub = new Publisher(name);
		pubRepo.save(newPub);
	}
	
	@Override
	public ArrayList<Publisher> retrieveAll() throws Exception {
		if (pubRepo.count() == 0) throw new Exception("There are no publishers to retrieve");
		ArrayList<Publisher> allPubs = (ArrayList<Publisher>) pubRepo.findAll();
		return allPubs;
	}

	@Override
	public Publisher retrieveById(long id) throws Exception {
		if (id <= 0) throw new Exception("Id cannot be negative or 0");
		if (!pubRepo.existsById(id)) throw new Exception("There are no publishers with the id: " + id);
		
		Publisher pubFromDB = pubRepo.findById(id).get();
		return pubFromDB;
	}

	@Override
	public void updateById(long id, String name) throws Exception {
		Publisher pubFromDB = retrieveById(id);
		
		if (name == null || name.isEmpty()) throw new Exception("The name cannot be empty");
		if (!pubFromDB.getName().equals(name)) pubFromDB.setName(name);
		pubRepo.save(pubFromDB);
	}
	
	@Override
	public void deleteById(long id) throws Exception {
		Publisher pubFromDB = retrieveById(id);
		pubRepo.delete(pubFromDB);	
	}
}
