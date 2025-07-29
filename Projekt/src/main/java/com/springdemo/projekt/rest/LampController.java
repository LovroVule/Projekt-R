package com.springdemo.projekt.rest;

import com.springdemo.projekt.dao.LampRepository;
import com.springdemo.projekt.domain.Lamp;
import com.springdemo.projekt.domain.Problem;
import com.springdemo.projekt.service.LampService;
import com.springdemo.projekt.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteLamp(@RequestParam String addres) {
        lampService.deleteByAddres(addres);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/working")
    public List<Lamp> listWorking() {return lampService.findAllWorking();}


    // dodavanje novih uličnih lampi u sustav (rade ispravno)(dodaje ih admin)
    @PostMapping("/add/Lamp")
    public void createLamp(@RequestBody Lamp lamp) {
        lamp.setStatus(1);
        lampService.save(lamp);
    }
}
