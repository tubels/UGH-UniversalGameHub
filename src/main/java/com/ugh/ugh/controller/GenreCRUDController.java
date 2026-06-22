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

import com.ugh.ugh.model.Genre;
import com.ugh.ugh.service.IGenreCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/genre/crud")
public class GenreCRUDController {

    private final IGenreCRUDService genreService;

    GenreCRUDController(IGenreCRUDService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all") // localhost:8080/genre/crud/all
    public String getControllerGetAllGenres(Model model) {

        try {
            ArrayList<Genre> allGenres = genreService.retrieveAll();
            model.addAttribute("package", allGenres);
            return ""; // TODO: add page to show all genres

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/one") // localhost:8080/genre/crud/one?id=1
    public String getControllerGetOneGenreById(@RequestParam(name = "id") long id, Model model) {
        try {
            Genre genreFound = genreService.retrieveById(id);
            model.addAttribute("package", genreFound);
            return ""; // TODO: add page to show 1 genre by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/genre/crud/all/1
    public String getControllerGetOneGenreById2(@PathVariable(name = "id") long id, Model model) {

        try {
            Genre genreFound = genreService.retrieveById(id);
            model.addAttribute("package", genreFound);
            return ""; // TODO: add page to show 1 genre by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/create") // localhost:8080/genre/crud/create
    public String getControllerCreateNewGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "";// TODO: add create new genre page
    }

    @PostMapping("/create")
    public String postControllerCreateNewGenre(@Valid Genre genre, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "create";
        }

        try {
            genreService.create(genre.getName());

            return ""; // TODO: return created genre?
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

    @GetMapping("/update/{id}") // localhost:8080/genre/crud/update/1
    public String getControllerUpdateGenreById(@PathVariable(name = "id") long id, Model model) {
        try {
            Genre genreToUpdate = genreService.retrieveById(id);
            model.addAttribute("genre", genreToUpdate);
            return ""; // TODO: add update genre page
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdateGenreById(@PathVariable(name = "id") long id, @Valid Genre genre,
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
            genreService.updateById(id, genre.getName());
            return ""; // TODO: something idk
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/genre/crud/delete/3
    public String getControllerDeleteGenreById(@PathVariable(name = "id") long id, Model model) {
        try {
            genreService.deleteById(id);
            model.addAttribute("package", genreService.retrieveAll());
            return ""; // TODO: redirect somewhere

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

}