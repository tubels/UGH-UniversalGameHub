package com.ugh.ugh.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ugh.ugh.enums.GameStatus;
import com.ugh.ugh.repo.IUserRepo;
import com.ugh.ugh.service.IFilterService;

@Controller
@RequestMapping("/filter")
public class FilterController {

    private final IUserRepo userRepo;
    private final IFilterService filterService;

    FilterController(IFilterService filterService, IUserRepo userRepo) {
        this.filterService = filterService;
        this.userRepo = userRepo;
    }

    @PostMapping("/game")
    public String getGamesByKeyword(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
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
            model.addAttribute("users", userRepo.findAll());
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

    @GetMapping("/usergames")
    public String getUserGameTotalPrice(@RequestParam(name = "id", required = false) long id, Model model) {
        try {
            model.addAttribute("package", filterService.filterUserAllGames(id));
            model.addAttribute("users", userRepo.findAll());
            return "show-all-usergame";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/rating")
    public String getReviewByRating(@RequestParam(name = "rating", required = false) int rating, Model model) {
        try {
            model.addAttribute("package", filterService.filterReviewByRating(rating));
            return "show-all-reviews";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

}