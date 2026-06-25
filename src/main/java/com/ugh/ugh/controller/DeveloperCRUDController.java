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

import com.ugh.ugh.model.Developer;
import com.ugh.ugh.service.IDeveloperCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/developer/crud")
public class DeveloperCRUDController {

    private final IDeveloperCRUDService developerService;

    DeveloperCRUDController(IDeveloperCRUDService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/all") // localhost:8080/developer/crud/all
    public String getControllerGetAllDevelopers(Model model) {

        try {
            ArrayList<Developer> allDevelopers = developerService.retrieveAll();
            model.addAttribute("package", allDevelopers);
            return "show-all-developers";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/one") // localhost:8080/developer/crud/one?id=1
    public String getControllerGetOneDeveloperById(@RequestParam(name = "id") long id, Model model) {
        try {
            Developer developerFound = developerService.retrieveById(id);
            model.addAttribute("package", developerFound);
            return "show-one-developer";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/developer/crud/all/1
    public String getControllerGetOneDeveloperById2(@PathVariable(name = "id") long id, Model model) {

        try {
            Developer developerFound = developerService.retrieveById(id);
            model.addAttribute("package", developerFound);
            return "show-one-developer";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/create") // localhost:8080/developer/crud/create
    public String getControllerCreateNewDeveloper(Model model) {
        model.addAttribute("developer", new Developer());
        return "create-developer";
    }

    @PostMapping("/create")
    public String postControllerCreateNewDeveloper(@Valid Developer developer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "create-developer";
        }

        try {
            developerService.create(developer.getName());

            return "redirect:/developer/crud/all";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}") // localhost:8080/developer/crud/update/1
    public String getControllerUpdateDeveloperById(@PathVariable(name = "id") long id, Model model) {
        try {
            Developer developerToUpdate = developerService.retrieveById(id);
            model.addAttribute("developer", developerToUpdate);
            model.addAttribute("id", id);
            return "update-developer";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdateDeveloperById(@PathVariable(name = "id") long id, @Valid Developer developer,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            try {
            	model.addAttribute("id", id);
                return "update-developer";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }
        }

        try {
            developerService.updateById(id, developer.getName());
            return "redirect:/developer/crud/all";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/developer/crud/delete/3
    public String getControllerDeleteDeveloperById(@PathVariable(name = "id") long id, Model model) {
        try {
            developerService.deleteById(id);
            model.addAttribute("package", developerService.retrieveAll());
            return "show-all-developers";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
}