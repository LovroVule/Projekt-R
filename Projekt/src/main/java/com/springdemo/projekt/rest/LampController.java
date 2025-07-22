package com.springdemo.projekt.rest;

import com.springdemo.projekt.dao.LampRepository;
import com.springdemo.projekt.domain.Lamp;
import com.springdemo.projekt.domain.Problem;
import com.springdemo.projekt.service.LampService;
import com.springdemo.projekt.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/lamps")
public class LampController {
    @Autowired
    private ProblemService problemService;
    @Autowired
    private LampService lampService;

    @GetMapping("/all")
    public List<Lamp> listLamps() {
        return lampService.findAll();
    }

    @GetMapping("/notWorking")
    public List<Lamp> listNotWorking() {return lampService.findAllNonWorking();}

    @DeleteMapping("/delete")
    public void deleteLampByAdress(@RequestParam String adress) {
        List<Problem> l = problemService.listAll();
        for (Problem p : l) {
            if(p.getAdress().equals(adress)) {
                problemService.deleteProblemById(p.getId());
                return;
            }
        }
        throw new IllegalArgumentException("No problem found with address " + adress);
    }

    @GetMapping("/working")
    public List<Lamp> listWorking() {return lampService.findAllWorking();}


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
                return problemService.createProblem(problem);}
        } else{
            throw new RequestDeniedException("Ulična lampa već u sustavu");
        }
    }
}
