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

    @GetMapping("/all")
    public List<Problem> listProblem() {
        return problemService.listAll();
    }

    @GetMapping("/notWorking")
    public List<Problem> listNotWorking() {return problemService.findNotWorking();}

    @GetMapping("/working")
    public List<Problem> listWorking() {return problemService.findWorking();}


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
            problem.setStatus("0");
            problemService.createProblem(problem);
        }
    }

    // dodavanje novih uličnih lampi u sustav (rade ispravno)(dodaje ih admin)
    @PostMapping("/addAdmin")
    public Problem createLamp(@RequestParam String adresa,
                              @RequestParam Double usage,
                              @RequestParam Double workHours)
    {
        Problem b = problemService.findProblemByAdress(adresa);
        if(b == null) {
            if(usage < 0 || workHours < 0){throw new IllegalArgumentException("Ne može biti negativno");
            } else{
                Problem problem = new Problem();
                problem.setAdress(adresa);
                problem.setCreatedOn(Timestamp.from(Instant.now()));
                problem.setDescription("");
                problem.setName("");
                problem.setPhoneNumber("");
                problem.setStatus("1");
                problem.setUsage(usage);
                problem.setWorkHours(workHours);
                return problemService.createProblem(problem);}
        } else{
            throw new RequestDeniedException("Ulična lampa već u sustavu");
        }
    }
}

