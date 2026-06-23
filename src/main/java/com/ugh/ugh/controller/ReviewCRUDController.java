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

import com.ugh.ugh.model.Review;
import com.ugh.ugh.service.IReviewCRUDService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/review/crud")
public class ReviewCRUDController {

    private final IReviewCRUDService reviewService;

    ReviewCRUDController(IReviewCRUDService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/all") // localhost:8080/review/crud/all
    public String getControllerGetAllReviews(Model model) {

        try {
            ArrayList<Review> allReviews = reviewService.retrieveAll();
            model.addAttribute("package", allReviews);
            return "show-all-reviews";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/one") // localhost:8080/review/crud/one?id=1
    public String getControllerGetOneReviewById(@RequestParam(name = "id") long id, Model model) {
        try {
            Review reviewFound = reviewService.retrieveById(id);
            model.addAttribute("package", reviewFound);
            return "show-one-review";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/all/{id}") // localhost:8080/review/crud/all/1
    public String getControllerGetOneReviewById2(@PathVariable(name = "id") long id, Model model) {

        try {
            Review reviewFound = reviewService.retrieveById(id);
            model.addAttribute("package", reviewFound);
            return "show-one-review";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/create") // localhost:8080/review/crud/create
    public String getControllerCreateNewReview(Model model) {
        model.addAttribute("review", new Review());
        return "create-review";
    }

    @PostMapping("/create")
    public String postControllerCreateNewReview(@Valid Review review, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "create-review";
        }

        try {
            reviewService.create(review.getTitle(), review.getRating(), review.getDescription(), review.getUser(),
                    review.getGame());

            return "redirect:/review/crud/all";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/update/{id}") // localhost:8080/review/crud/update/1
    public String getControllerUpdateReviewById(@PathVariable(name = "id") long id, Model model) {
        try {
            Review reviewToUpdate = reviewService.retrieveById(id);
            model.addAttribute("review", reviewToUpdate);
            return "update-review";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerUpdateReviewById(@PathVariable(name = "id") long id, @Valid Review review,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            try {
                return "update-review";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }
        }

        try {
            reviewService.updateById(id, review.getTitle(), review.getRating(), review.getDescription(),
                    review.getUser(), review.getGame());
            return "redirect:/review/crud/all";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/delete/{id}") // localhost:8080/review/crud/delete/3
    public String getControllerDeleteReviewById(@PathVariable(name = "id") long id, Model model) {
        try {
            reviewService.deleteById(id);
            model.addAttribute("package", reviewService.retrieveAll());
            return "show-all-reviews";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
}