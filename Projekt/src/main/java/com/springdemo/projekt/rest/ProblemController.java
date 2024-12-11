package com.springdemo.projekt.rest;

import com.springdemo.projekt.domain.Problem;
import com.springdemo.projekt.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Controller
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @GetMapping("")
    public List<Problem> listStudents() {
        return problemService.listAll();
    }

    @PostMapping("/add")
    public String createProblem(@RequestParam String name,
                                 @RequestParam String adress,
                                 @RequestParam String phoneNumber,
                                 @RequestParam String description)
    {
        Problem problem = new Problem();
        problem.setName(name);
        problem.setAdress(adress);
        problem.setPhoneNumber(phoneNumber != null ? phoneNumber : "Nepoznato");
        problem.setDescription(description != null ? description : "Nepoznato");
        problem.setCreatedOn(Timestamp.from(Instant.now()));
        problemService.createProblem(problem);

        return "redirect:/predaja.html";

    }

    @PostMapping("/add2")
    public Problem createProblem(@RequestBody Problem problem) {
        return problemService.createProblem(problem);
    }

}

