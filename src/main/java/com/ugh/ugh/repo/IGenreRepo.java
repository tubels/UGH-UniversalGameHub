package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Genre;

public interface IGenreRepo extends CrudRepository<Genre, Long> {

	boolean existsByName(String name);

}
