package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Developer;

public interface IDeveloperRepo extends CrudRepository<Developer, Long> {

	boolean existsByName(String name);

}
