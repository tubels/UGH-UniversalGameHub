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

import com.ugh.ugh.model.Publisher;
import com.ugh.ugh.service.IPublisherCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/publisher/crud")
public class PublisherCRUDController {

    private final IPublisherCRUDService publisherService;

    PublisherCRUDController(IPublisherCRUDService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/all") // localhost:8080/publisher/crud/all
    public String getControllerGetAllPublishers(Model model) {

        try {
            ArrayList<Publisher> allPublishers = publisherService.retrieveAll();
            model.addAttribute("package", allPublishers);
            return ""; // TODO: add page to show all publishers

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/one") // localhost:8080/publisher/crud/one?id=1
    public String getControllerGetOnePublisherById(@RequestParam(name = "id") long id, Model model) {
        try {
            Publisher publisherFound = publisherService.retrieveById(id);
            model.addAttribute("package", publisherFound);
            return ""; // TODO: add page to show 1 publisher by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/publisher/crud/all/1
    public String getControllerGetOnePublisherById2(@PathVariable(name = "id") long id, Model model) {

        try {
            Publisher publisherFound = publisherService.retrieveById(id);
            model.addAttribute("package", publisherFound);
            return ""; // TODO: add page to show 1 publisher by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/create") // localhost:8080/publisher/crud/create
    public String getControllerCreateNewPublisher(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "";// TODO: add create new publisher page
    }

    @PostMapping("/create")
    public String postControllerCreateNewPublisher(@Valid Publisher publisher, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "create";
        }

        try {
            publisherService.create(publisher.getName());

            return ""; // TODO: return created publisher?
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

    @GetMapping("/update/{id}") // localhost:8080/publisher/crud/update/1
    public String getControllerUpdatePublisherById(@PathVariable(name = "id") long id, Model model) {
        try {
            Publisher publisherToUpdate = publisherService.retrieveById(id);
            model.addAttribute("publisher", publisherToUpdate);
            return ""; // TODO: add update publisher page
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdatePublisherById(@PathVariable(name = "id") long id, @Valid Publisher publisher,
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
            publisherService.updateById(id, publisher.getName());
            return ""; // TODO: something idk
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/publisher/crud/delete/3
    public String getControllerDeletePublisherById(@PathVariable(name = "id") long id, Model model) {
        try {
            publisherService.deleteById(id);
            model.addAttribute("package", publisherService.retrieveAll());
            return ""; // TODO: redirect somewhere

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

}