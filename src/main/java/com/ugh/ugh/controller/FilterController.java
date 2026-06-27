package com.ugh.ugh.controller;

import java.time.LocalDate;
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
import com.ugh.ugh.model.Game;
import com.ugh.ugh.repo.IDeveloperRepo;
import com.ugh.ugh.repo.IGenreRepo;
import com.ugh.ugh.repo.IPublisherRepo;
import com.ugh.ugh.service.IGameCRUDService;
import com.ugh.ugh.service.IFilterService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/filter")
public class FilterController {

    final IFilterService filterService;

    FilterController(IFilterService filterService) {
        this.filterService = filterService;
    }

    @PostMapping("/game")
    public String getGamesByKeyword(@RequestParam(name = "keyword") String keyword, Model model) {
        try {
            model.addAttribute("package", filterService.filterGamesByKeyword(keyword));
            return "show-all-games";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/gamestatus")
    public String getUserGamesByGameStatus(@RequestParam(name = "status", required = false) GameStatus gameStatus,
            Model model) {
        try {
            model.addAttribute("package", filterService.filterUserGameByGameStatus(gameStatus));
            return "show-all-usergame";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/gamedate")
    public String getGamesByReleaseDate(@RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate, Model model) {
        try {
            model.addAttribute("package", filterService.filterGameByStartAndEndDate(startDate, endDate));
            return "show-all-games";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

}