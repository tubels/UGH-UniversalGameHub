package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.User;

public interface IUserRepo extends CrudRepository<User, Long> {

}
