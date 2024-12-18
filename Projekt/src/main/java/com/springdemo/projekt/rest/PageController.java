package com.springdemo.projekt.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "index"; // "User interface"
    }

    @GetMapping("/map")
    public String showMapPage() {
        return "map"; // "Šta na FERu bum vidil"
    }
}
