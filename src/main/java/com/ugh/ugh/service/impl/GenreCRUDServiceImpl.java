package com.ugh.ugh.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.model.Genre;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.service.IGenreCRUDService;

@Service
public class GenreCRUDServiceImpl implements IGenreCRUDService {

	private final IGenreRepo genreRepo;
	private final IGameRepo gameRepo;

	GenreCRUDServiceImpl(IGenreRepo genreRepo, IGameRepo gameRepo) {
		this.genreRepo = genreRepo;
		this.gameRepo = gameRepo;
	}

	@Override
	public void create(String name) throws Exception {
		if (name == null || name.isEmpty())
			throw new Exception("The name cannot be empty");
		if (genreRepo.existsByName(name))
			throw new Exception("A genre with this name already exists");
		Genre newGen = new Genre(name);
		genreRepo.save(newGen);
	}

	@Override
	public ArrayList<Genre> retrieveAll() throws Exception {
		if (genreRepo.count() == 0)
			throw new Exception("There are no genres to retrieve");
		ArrayList<Genre> allGen = (ArrayList<Genre>) genreRepo.findAll();
		return allGen;
	}

	@Override
	public Genre retrieveById(long id) throws Exception {
		if (id <= 0)
			throw new Exception("Id cannot be negative or 0");
		if (!genreRepo.existsById(id))
			throw new Exception("There are no genres with the id: " + id);

		Genre genreFromDB = genreRepo.findById(id).get();
		return genreFromDB;
	}

	@Override
	public void updateById(long id, String name) throws Exception {
		Genre genreFromDB = retrieveById(id);

		if (name == null || name.isEmpty())
			throw new Exception("The name cannot be empty");
		if (!genreFromDB.getName().equals(name))
			genreFromDB.setName(name);
		genreRepo.save(genreFromDB);
	}

	@Override
	public void deleteById(long id) throws Exception {
		Genre genFromDB = retrieveById(id);
		if (gameRepo.existsByGenres(genFromDB)) {

			Collection<Game> allGamesWithGenre = genFromDB.getGames();

			for (Game game : allGamesWithGenre) {
				game.getGenres().remove(genFromDB);
				gameRepo.save(game);
			}

		}
		genreRepo.delete(genFromDB);
	}
}
