package com.ugh.ugh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.service.IGameCRUDService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/game/crud") // TODO: test this shit 
public class GameCRUDController {

	private final IGameCRUDService gameService;

	GameCRUDController(IGameCRUDService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/all") // localhost:8080/game/crud/all
	public ResponseEntity<?> getControllerGetAllGames() {

		try {
			ArrayList<Game> allGames = gameService.retrieveAll();
			ResponseEntity<ArrayList<Game>> response = new ResponseEntity<ArrayList<Game>>(allGames, HttpStatus.OK);
			return response;

		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping("/one") // localhost:8080/game/crud/one?id=1
	public ResponseEntity<?> getControllerGetOneGameById(@RequestParam(name = "id") long id) {
		try {
			Game gameFound = gameService.retrieveById(id);

			ResponseEntity<Game> response = new ResponseEntity<Game>(gameFound, HttpStatus.OK);
			return response;

		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/game/crud/all/1
	public ResponseEntity<?> getControllerGetOneGameById2(@PathVariable(name = "id") long id) {

		try {
			Game gameFound = gameService.retrieveById(id);

			ResponseEntity<Game> response = new ResponseEntity<Game>(gameFound, HttpStatus.OK);
			return response;

		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> postControllerCreateNewGame(@RequestBody @Valid Game game, BindingResult result) {

		if (result.hasErrors()) {
			ResponseEntity<List<ObjectError>> response = new ResponseEntity<>(result.getAllErrors(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}

		try {
			gameService.create(game.getTitle(), game.getPrice(), game.getDescription(), game.getReleaseDate(),
					game.getDeveloper(), game.getPublisher(), game.getGenres());

			ArrayList<Game> allGames = gameService.retrieveAll();
			ResponseEntity<ArrayList<Game>> response = new ResponseEntity<ArrayList<Game>>(allGames,
					HttpStatus.OK);
			return response;
		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> postControllerUpdateGameById(@PathVariable(name = "id") long id,
			@RequestBody @Valid Game game, BindingResult result) {
		if (result.hasErrors()) {
			ResponseEntity<List<ObjectError>> response = new ResponseEntity<>(result.getAllErrors(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;

		}

		try {
			gameService.updateById(id, game.getTitle(), game.getPrice(), game.getDescription(),
					game.getReleaseDate(),
					game.getDeveloper(), game.getPublisher(), game.getGenres());
			Game gameFromDB = gameService.retrieveById(id);
			ResponseEntity<Game> response = new ResponseEntity<Game>(gameFromDB, HttpStatus.OK);

			return response;

		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}
	}

	@DeleteMapping("/delete/{id}") // localhost:8080/game/crud/delete/3
	public ResponseEntity<?> getControllerDeleteGameById(@PathVariable(name = "id") long id) {
		try {
			gameService.deleteById(id);
			ArrayList<Game> allGames = gameService.retrieveAll();
			ResponseEntity<ArrayList<Game>> response = new ResponseEntity<ArrayList<Game>>(allGames, HttpStatus.OK);
			return response;

		} catch (Exception e) {
			ResponseEntity<String> response = new ResponseEntity<String>(e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
			return response;
		}

	}

}