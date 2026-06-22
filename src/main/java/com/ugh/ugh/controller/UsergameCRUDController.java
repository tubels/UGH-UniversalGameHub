package com.ugh.ugh.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ugh.ugh.model.UserGame;
import com.ugh.ugh.service.IUsergameCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usergame/crud")
public class UsergameCRUDController {

    private final IUsergameCRUDService userGameService;

    UsergameCRUDController(IUsergameCRUDService userGameService) {
        this.userGameService = userGameService;
    }

    @GetMapping("/all") // localhost:8080/usergame/crud/all
    public String getControllerGetAllUserGames(Model model) {

        try {
            ArrayList<UserGame> allUserGames = userGameService.retrieveAll();
            model.addAttribute("package", allUserGames);
            return ""; // TODO: add page to show all userGames

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/one") // localhost:8080/usergame/crud/one?id=1
    public String getControllerGetOneUserGameById(@RequestParam(name = "id") long id, Model model) {
        try {
            UserGame userGameFound = userGameService.retrieveById(id);
            model.addAttribute("package", userGameFound);
            return ""; // TODO: add page to show 1 userGame by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/usergame/crud/all/1
    public String getControllerGetOneUserGameById2(@PathVariable(name = "id") long id, Model model) {

        try {
            UserGame userGameFound = userGameService.retrieveById(id);
            model.addAttribute("package", userGameFound);
            return ""; // TODO: add page to show 1 userGame by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/create") // localhost:8080/usergame/crud/create
    public String getControllerCreateNewUserGame(Model model) {
        model.addAttribute("userGame", new UserGame());
        return "";// TODO: add create new userGame page
    }

    @PostMapping("/create")
    public String postControllerCreateNewUserGame(@Valid UserGame userGame, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "create";
        }

        try {
            userGameService.create(userGame.getGameStatus(), userGame.getUser(), userGame.getGame());

            return ""; // TODO: return created userGame?
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

    @GetMapping("/update/{id}") // localhost:8080/usergame/crud/update/1
    public String getControllerUpdateUserGameById(@PathVariable(name = "id") long id, Model model) {
        try {
            UserGame userGameToUpdate = userGameService.retrieveById(id);
            model.addAttribute("userGame", userGameToUpdate);
            return ""; // TODO: add update userGame page
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdateUserGameById(@PathVariable(name = "id") long id, @Valid UserGame userGame,
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
            userGameService.updateById(id, userGame.getGameStatus(), userGame.getUser(), userGame.getGame());
            return ""; // TODO: something idk
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/usergame/crud/delete/3
    public String getControllerDeleteUserGameById(@PathVariable(name = "id") long id, Model model) {
        try {
            userGameService.deleteById(id);
            model.addAttribute("package", userGameService.retrieveAll());
            return ""; // TODO: redirect somewhere

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

}