package com.ugh.ugh.repo;

import org.springframework.data.repository.CrudRepository;

import com.ugh.ugh.model.Developer;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Genre;
import com.ugh.ugh.model.Publisher;

import java.util.ArrayList;
import java.util.List;

public interface IGameRepo extends CrudRepository<Game, Long> {

	boolean existsByTitle(String title);

	boolean existsByDeveloper(Developer developer);

	boolean existsByPublisher(Publisher publisher);

	boolean existsByGenres(Genre genre);

	ArrayList<Game> findByDescriptionContainingOrTitleContainingOrDeveloperNameContainingOrPublisherNameContainingOrGenresNameContaining(
			String keyword, String keyword2, String keyword3, String keyword4, String keyword5);
}
