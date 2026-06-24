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

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.model.UserGame;
import com.ugh.ugh.repo.IGameRepo;
import com.ugh.ugh.repo.IUserRepo;
import com.ugh.ugh.service.IUsergameCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usergame/crud")
public class UsergameCRUDController {

	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private IGameRepo gameRepo;
	
    private final IUsergameCRUDService userGameService;

    UsergameCRUDController(IUsergameCRUDService userGameService) {
        this.userGameService = userGameService;
    }

    @GetMapping("/all") // localhost:8080/usergame/crud/all
    public String getControllerGetAllUserGames(Model model) {

        try {
            ArrayList<UserGame> allUserGames = userGameService.retrieveAll();
            model.addAttribute("package", allUserGames);
            return "show-all-usergame";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/one") // localhost:8080/usergame/crud/one?id=1
    public String getControllerGetOneUserGameById(@RequestParam(name = "id") long id, Model model) {
        try {
            UserGame userGameFound = userGameService.retrieveById(id);
            model.addAttribute("package", userGameFound);
            return "show-one-usergame";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/usergame/crud/all/1
    public String getControllerGetOneUserGameById2(@PathVariable(name = "id") long id, Model model) {

        try {
            UserGame userGameFound = userGameService.retrieveById(id);
            model.addAttribute("package", userGameFound);
            return "show-one-usergame";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/create") // localhost:8080/usergame/crud/create
    public String getControllerCreateNewUserGame(Model model) {
        model.addAttribute("userGame", new UserGame());
        loadDropdowns(model);
        return "create-usergame";
    }

    @PostMapping("/create")
    public String postControllerCreateNewUserGame(@Valid UserGame userGame, BindingResult result,
    								@RequestParam long userId, @RequestParam long gameId, Model model) {

        if (result.hasErrors()) {
        	loadDropdowns(model);
            return "create-usergame";
        }
        
        try {
            userGameService.create(userGame.getGameStatus(), userId, gameId);

            return "redirect:/usergame/crud/all";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}") // localhost:8080/usergame/crud/update/1
    public String getControllerUpdateUserGameById(@PathVariable(name = "id") long id, Model model) {
        try {
            UserGame userGameToUpdate = userGameService.retrieveById(id);
            model.addAttribute("userGame", userGameToUpdate);
            model.addAttribute("id", id);
            loadDropdowns(model);
            return "update-usergame";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdateUserGameById(@PathVariable(name = "id") long id, @Valid UserGame userGame,
            BindingResult result, @RequestParam long userId, @RequestParam long gameId, Model model) {
        if (result.hasErrors()) {
            try {
            	model.addAttribute("id", id);
            	loadDropdowns(model);
                return "update-usergame";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }
        }

        try {
            userGameService.updateById(id, userGame.getGameStatus(), userId, gameId);
            return "redirect:/usergame/crud/all";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/usergame/crud/delete/3
    public String getControllerDeleteUserGameById(@PathVariable(name = "id") long id, Model model) {
        try {
            userGameService.deleteById(id);
            model.addAttribute("package", userGameService.retrieveAll());
            return "redirect:/usergame/crud/all";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
    
    private void loadDropdowns(Model model) {
		model.addAttribute("users", userRepo.findAll());
		model.addAttribute("games", gameRepo.findAll());
		model.addAttribute("statuses", GameStatus.values());
	}
}