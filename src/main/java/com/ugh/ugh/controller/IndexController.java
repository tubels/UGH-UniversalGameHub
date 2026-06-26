package com.ugh.ugh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping("") // localhost:8080/
    public String getIndexPage() {
        return "index";
    }

    @GetMapping("/crud")
    public String getCrudPage() {
        return "index-crud";
    }

    @GetMapping("/filter")
    public String getFilterPage() {
        return "index-filter";
    }
}