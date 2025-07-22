package com.springdemo.projekt.service.impl;


import com.springdemo.projekt.dao.*;
import com.springdemo.projekt.domain.*;
import com.springdemo.projekt.service.LampService;
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
    public void sava(Data_dim data_dim) {data_dim_Repository.save(data_dim);}

    @Override
    public void save(Data_traffic data_traffic) {data_traffic_Repository.save(data_traffic);}

    @Override
    public void save(Data_weather data_weather) {data_weather_Repository.save(data_weather);}

    @Override
    public void save(Data_people data_people) {data_people_Repository.save(data_people);}

    @Override
    public void delete(Data_dim data_dim) {data_dim_Repository.delete(data_dim);}

    @Override
    public void delete(Data_traffic data_traffic) {data_traffic_Repository.delete(data_traffic);}

    @Override
    public void delete(Data_weather data_weather) {data_weather_Repository.delete(data_weather);}

    @Override
    public void delete(Data_people data_people) {data_people_Repository.delete(data_people);}

}
