package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Publisher;

public interface IPublisherRepo extends CrudRepository<Publisher, Long> {

	boolean existsByName(String name);

}
