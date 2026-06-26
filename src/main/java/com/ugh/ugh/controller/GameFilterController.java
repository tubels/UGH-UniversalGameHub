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
import com.ugh.ugh.service.IGameFilter;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/filter")
public class GameFilterController {

    final IGameFilter gameFilter;

    GameFilterController(IGameFilter gameFilter) {
        this.gameFilter = gameFilter;
    }

    @GetMapping("/{keyword}")
    public String getGamesByKeyword(@PathVariable(name = "keyword") String keyword, Model model) {
        try {
            model.addAttribute("package", gameFilter.filterGamesByKeyword(keyword));
            return "show-all-games";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

}