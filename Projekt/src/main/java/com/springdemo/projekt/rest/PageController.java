package com.springdemo.projekt.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class PageController {

    @GetMapping("/map")
    public String showMapPage() {
        return "map";
    }

    @GetMapping("/index")
    public String showAdminPage() {return "index";}

    @GetMapping("/zahvala")
    public String showUserPage() {
        return "predaja";
    }

}

