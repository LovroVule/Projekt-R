package com.springdemo.projekt.service.impl;


import com.springdemo.projekt.dao.*;
import com.springdemo.projekt.domain.*;
import com.springdemo.projekt.service.LampService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class LampServiceJpa implements LampService {

    @Autowired
    private LampRepository lampRepository;
    @Autowired
    private Data_dim_Repository data_dim_Repository;
    @Autowired
    private Data_people_Repository data_people_Repository;
    @Autowired
    private Data_weather_Repository data_weather_Repository;
    @Autowired
    private Data_traffic_Repository data_traffic_Repository;


    @Override
    public List<Lamp> findAll() {return lampRepository.findAll();}

    public Lamp findById(Long id) {
        return lampRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Lamp lamp) {lampRepository.save(lamp);}

    @Override
    public void delete(Lamp lamp) {lampRepository.delete(lamp);}

    @Override
    public List<Lamp> findAllNonWorking() {
        return lampRepository.findAll().stream().filter(lamp -> lamp.getStatus() == 1).toList();
    }

    @Override
    public List<Lamp> findAllWorking() {
        return lampRepository.findAll().stream().filter(lamp -> lamp.getStatus() == 0).toList();
    }

    @Override
    @Transactional
    public void deleteByAddres(String addres) {
        lampRepository.deleteByAddres(addres);
    }

}
