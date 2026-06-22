package com.ugh.ugh.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ugh.ugh.model.Developer;
import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Genre;
import com.ugh.ugh.model.Publisher;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.service.IGameCRUDService;

@Service
public class GameCRUDServiceImpl implements IGameCRUDService {
	
	@Autowired
	private IGameRepo gameRepo;
	
	@Override
	public void create(String title, float price, String description, LocalDate releaseDate, 
								Developer developer, Publisher publisher, Collection<Genre> genres) throws Exception {
		if (title == null || title.isEmpty()
				|| price < 0 || price > 1234
				|| description == null || description.isEmpty()
				|| releaseDate == null 
				|| developer == null
				|| publisher == null
				|| genres == null || genres.isEmpty()) {
			throw new Exception("One or more of the input fields are incorrect or empty");
		}
		
		if(gameRepo.existsByTitle(title)) throw new Exception("Game with this title already exists");
		
		Game newGame = new Game(title, price, description, releaseDate, developer, publisher, genres);
		gameRepo.save(newGame);
		
	}
	
	@Override
	public ArrayList<Game> retrieveAll() throws Exception {
		if (gameRepo.count() == 0) throw new Exception("There are no games to retrieve");
		ArrayList<Game> allGames = (ArrayList<Game>) gameRepo.findAll();
		return allGames;
	}
	
	@Override
	public Game retrieveById(long id) throws Exception {
		if (id <= 0) throw new Exception("Id cannot be negative or 0");
		if (!gameRepo.existsById(id)) throw new Exception("There are no games with the id: " + id);
		
		Game gameFromDB = gameRepo.findById(id).get();
		return gameFromDB;
	}

	@Override
	public void updateById(long id, String title, float price, String description, LocalDate releaseDate, 
							Developer developer, Publisher publisher, Collection<Genre> genres) throws Exception {
		Game gameFromDB = retrieveById(id);
		
		if (title == null || title.isEmpty()
				|| price < 0 || price > 1234
				|| description == null || description.isEmpty()
				|| releaseDate == null 
				|| developer == null
				|| publisher == null
				|| genres == null || genres.isEmpty()) {
			throw new Exception("One or more of the input fields are incorrect or empty");
		}
		if (!gameFromDB.getTitle().equals(title)) gameFromDB.setTitle(title);
		if (gameFromDB.getPrice() != price) gameFromDB.setPrice(price);
		if (!gameFromDB.getDescription().equals(description)) gameFromDB.setDescription(description);
		if (!gameFromDB.getReleaseDate().equals(releaseDate)) gameFromDB.setReleaseDate(releaseDate);
		if (!gameFromDB.getPublisher().equals(publisher)) gameFromDB.setPublisher(publisher);
		if (!gameFromDB.getDeveloper().equals(developer)) gameFromDB.setDeveloper(developer);
		if (!gameFromDB.getGenres().equals(genres)) gameFromDB.setGenres(genres);
	
		gameRepo.save(gameFromDB);
	}
	
	@Override
	public void deleteById(long id) throws Exception {
		Game gameFromDB = retrieveById(id);
		gameRepo.delete(gameFromDB);
	}
}
