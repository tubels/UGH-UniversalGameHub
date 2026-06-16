package com.ugh.ugh.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ugh.ugh.model.Game;
import com.ugh.ugh.service.IGameCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/game/crud")
public class GameCRUDController {

	private final IGameCRUDService gameService;

	GameCRUDController(IGameCRUDService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/all") // localhost:8080/game/crud/all
	public String getControllerGetAllGames(Model model) {

		try {
			ArrayList<Game> allGames = gameService.retrieveAll();
			model.addAttribute("package", allGames);
			return ""; // TODO: add page to show all games

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}
	}

	@GetMapping("/one") // localhost:8080/game/crud/one?id=1
	public String getControllerGetOneGameById(@RequestParam(name = "id") long id, Model model) {
		try {
			Game gameFound = gameService.retrieveById(id);
			model.addAttribute("package", gameFound);
			return ""; // TODO: add page to show 1 game by id
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/game/crud/all/1
	public String getControllerGetOneGameById2(@PathVariable(name = "id") long id, Model model) {

		try {
			Game gameFound = gameService.retrieveById(id);
			model.addAttribute("package", gameFound);
			return ""; // TODO: add page to show 1 game by id
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}
	}

	@GetMapping("/create") // localhost:8080/game/crud/create
	public String getControllerCreateNewGame(Model model) {
		model.addAttribute("game", new Game());
		return "";// TODO: add create new game page
	}

	@PostMapping("/create")
	public String postControllerCreateNewGame(@Valid Game game, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "create";
		}

		try {
			gameService.create(game.getTitle(), game.getPrice(), game.getDescription(), game.getReleaseDate(),
					game.getDeveloper(), game.getPublisher(), game.getGenres());

			return ""; // TODO: return created game?
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}

	}

	@GetMapping("/update/{id}") // localhost:8080/game/crud/update/1
	public String getControllerUpdateGameById(@PathVariable(name = "id") long id, Model model) {
		try {
			Game gameToUpdate = gameService.retrieveById(id);
			model.addAttribute("game", gameToUpdate);
			return ""; // TODO: add update game page
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}
	}

	@PostMapping("/update/{id}")
	public String postControllerUpdateGameById(@PathVariable(name = "id") long id, @Valid Game game,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			try {
				return ""; // TODO: return to update page
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return ""; // TODO: add error page
			}

		}

		try {
			gameService.updateById(id, game.getTitle(), game.getPrice(), game.getDescription(),
					game.getReleaseDate(), game.getDeveloper(), game.getPublisher(), game.getGenres());
			return ""; // TODO: something idk
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}
	}

	@GetMapping("/delete/{id}") // localhost:8080/game/crud/delete/3
	public String getControllerDeleteGameById(@PathVariable(name = "id") long id, Model model) {
		try {
			gameService.deleteById(id);
			model.addAttribute("package", gameService.retrieveAll());
			return ""; // TODO: redirect somewhere

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return ""; // TODO: add error page
		}

	}

}