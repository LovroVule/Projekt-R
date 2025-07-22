package com.springdemo.projekt.rest;

import com.springdemo.projekt.domain.Problem;
import com.springdemo.projekt.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/problems")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @PostMapping("/add") // Za korisnika koji prijavljuje kvar
    public void createProblem(@RequestParam String name,
                              @RequestParam String adress,
                              @RequestParam String phoneNumber,
                              @RequestParam String description)
    {
        Problem problem = problemService.findProblemByAdress(adress); // provjerava postoji li ulična lampa u bp

        if (problem == null) {throw new RequestDeniedException( // ako ne
                "Ulična rasvjeta s adresom " + adress + " ne postoji");
        }

        else{ // ako postoji postavi ime, adresu broj, opis i promijeni status rada u 0
            problem.setName(name);
            problem.setAdress(adress);
            problem.setPhoneNumber(phoneNumber != null ? phoneNumber : "Nepoznato");
            problem.setDescription(description != null ? description : "Nepoznato");
            problemService.createProblem(problem);
        }
    }
}

