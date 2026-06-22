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

import com.ugh.ugh.model.User;
import com.ugh.ugh.service.IUserCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/crud")
public class UserCRUDController {

    private final IUserCRUDService userService;

    UserCRUDController(IUserCRUDService userService) {
        this.userService = userService;
    }

    @GetMapping("/all") // localhost:8080/user/crud/all
    public String getControllerGetAllUsers(Model model) {

        try {
            ArrayList<User> allUsers = userService.retrieveAll();
            model.addAttribute("package", allUsers);
            return ""; // TODO: add page to show all users

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/one") // localhost:8080/user/crud/one?id=1
    public String getControllerGetOneUserById(@RequestParam(name = "id") long id, Model model) {
        try {
            User userFound = userService.retrieveById(id);
            model.addAttribute("package", userFound);
            return ""; // TODO: add page to show 1 user by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/user/crud/all/1
    public String getControllerGetOneUserById2(@PathVariable(name = "id") long id, Model model) {

        try {
            User userFound = userService.retrieveById(id);
            model.addAttribute("package", userFound);
            return ""; // TODO: add page to show 1 user by id
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/create") // localhost:8080/user/crud/create
    public String getControllerCreateNewUser(Model model) {
        model.addAttribute("user", new User());
        return "";// TODO: add create new user page
    }

    @PostMapping("/create")
    public String postControllerCreateNewUser(@Valid User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "create";
        }

        try {
            userService.create(user.getUsername(), user.getEmail());

            return ""; // TODO: return created user?
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

    @GetMapping("/update/{id}") // localhost:8080/user/crud/update/1
    public String getControllerUpdateUserById(@PathVariable(name = "id") long id, Model model) {
        try {
            User userToUpdate = userService.retrieveById(id);
            model.addAttribute("user", userToUpdate);
            return ""; // TODO: add update user page
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdateUserById(@PathVariable(name = "id") long id, @Valid User user,
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
            userService.updateById(id, user.getUsername(), user.getEmail());
            return ""; // TODO: something idk
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/user/crud/delete/3
    public String getControllerDeleteUserById(@PathVariable(name = "id") long id, Model model) {
        try {
            userService.deleteById(id);
            model.addAttribute("package", userService.retrieveAll());
            return ""; // TODO: redirect somewhere

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return ""; // TODO: add error page
        }

    }

}