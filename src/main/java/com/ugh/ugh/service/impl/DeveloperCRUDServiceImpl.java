package com.ugh.ugh.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.ugh.ugh.model.Developer;
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.service.IDeveloperCRUDService;

public class DeveloperCRUDServiceImpl implements IDeveloperCRUDService {

	@Autowired
	private IDeveloperRepo devRepo;

	@Override
	public void create(String name) throws Exception {
		if (name == null || name.isEmpty()) throw new Exception("The name cannot be empty");
		if(devRepo.existsByName(name)) throw new Exception("Developer with this name already exists");
		Developer newDev = new Developer(name);
		devRepo.save(newDev);
	}
	
	@Override
	public ArrayList<Developer> retrieveAll() throws Exception {
		if (devRepo.count() == 0) throw new Exception("There are no developers to retrieve");
		ArrayList<Developer> allDevs = (ArrayList<Developer>) devRepo.findAll();
		return allDevs;
	}

	@Override
	public Developer retrieveById(long id) throws Exception {
		if (id <= 0) throw new Exception("Id cannot be negative or 0");
		if (!devRepo.existsById(id)) throw new Exception("There are no developers with the id: " + id);
		
		Developer devFromDB = devRepo.findById(id).get();
		return devFromDB;
	}

	@Override
	public void updateById(long id, String name) throws Exception {
		Developer devFromDB = retrieveById(id);
		
		if (name == null || name.isEmpty()) throw new Exception("The name cannot be empty");
		if (!devFromDB.getName().equals(name)) devFromDB.setName(name);
		devRepo.save(devFromDB);
	}
	
	@Override
	public void deleteById(long id) throws Exception {
		Developer devFromDB = retrieveById(id);
		devRepo.delete(devFromDB);	
	}
}
