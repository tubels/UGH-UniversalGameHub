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
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.service.IGameCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/game/crud")
public class GameCRUDController {
	
	@Autowired 
	private IDeveloperRepo devRepo;
	@Autowired
	private IPublisherRepo publRepo;
	@Autowired
	private IGenreRepo genreRepo;
	
	private final IGameCRUDService gameService;

	GameCRUDController(IGameCRUDService gameService) {
		this.gameService = gameService;
	}

	@GetMapping("/all") // localhost:8080/game/crud/all
	public String getControllerGetAllGames(Model model) {
		try {
			ArrayList<Game> allGames = gameService.retrieveAll();
			model.addAttribute("package", allGames);
			return "show-all-games";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/one") // localhost:8080/game/crud/one?id=1
	public String getControllerGetOneGameById(@RequestParam(name = "id") long id, Model model) {
		try {
			Game gameFound = gameService.retrieveById(id);
			model.addAttribute("package", gameFound);
			return "show-one-game";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/game/crud/all/1
	public String getControllerGetOneGameById2(@PathVariable(name = "id") long id, Model model) {
		try {
			Game gameFound = gameService.retrieveById(id);
			model.addAttribute("package", gameFound);
			return "show-one-game";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/create") // localhost:8080/game/crud/create
	public String getControllerCreateNewGame(Model model) {
		model.addAttribute("game", new Game());
		loadDropdowns(model);
		return "create-game";
	}

	@PostMapping("/create")
	public String postControllerCreateNewGame(@Valid Game game, BindingResult result,
								@RequestParam long developerId, @RequestParam long publisherId, @RequestParam(required = false) Long[] genreIds, Model model) {
		
		if (result.hasErrors()) {
			loadDropdowns(model);
			return "create-game";
		}
		
		try {
			gameService.create(game.getTitle(), game.getPrice(), game.getDescription(), game.getReleaseDate(),
					developerId, publisherId, genreIds);
			return "redirect:/game/crud/all";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/update/{id}") // localhost:8080/game/crud/update/1
	public String getControllerUpdateGameById(@PathVariable(name = "id") long id, Model model) {
		try {
			Game gameToUpdate = gameService.retrieveById(id);
			model.addAttribute("game", gameToUpdate);
			model.addAttribute("id", id);
			loadDropdowns(model);
			return "update-game";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}")
	public String postControllerUpdateGameById(@PathVariable(name = "id") long id, @Valid Game game, BindingResult result,
			 @RequestParam long developerId, @RequestParam long publisherId, @RequestParam(required=false) Long[] genreIds, Model model) {
		
		if (result.hasErrors()) {
			try {
				model.addAttribute("id", id);
				loadDropdowns(model);
				return "update-game";
			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
		try {
			gameService.updateById(id, game.getTitle(), game.getPrice(), game.getDescription(),
					game.getReleaseDate(), developerId, publisherId, genreIds);
			return "redirect:/game/crud/all";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/delete/{id}") // localhost:8080/game/crud/delete/3
	public String getControllerDeleteGameById(@PathVariable(name = "id") long id, Model model) {
		try {
			gameService.deleteById(id);
			model.addAttribute("package", gameService.retrieveAll());
			return "show-all-games";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	private void loadDropdowns(Model model) {
		model.addAttribute("developers", devRepo.findAll());
		model.addAttribute("publishers", publRepo.findAll());
		model.addAttribute("genres", genreRepo.findAll());
	}
}