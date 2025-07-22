package com.springdemo.projekt.service;

import com.springdemo.projekt.dao.Data_traffic_Repository;
import com.springdemo.projekt.domain.*;

import java.util.List;

public interface LampService {

    List<Lamp> findAll();
    Lamp findById(Long id);
    void save(Lamp lamp);
    void delete(Lamp lamp);
    List<Lamp> findAllNonWorking();
    List<Lamp> findAllWorking();

    void sava(Data_dim data_dim);
    void save(Data_traffic data_traffic);
    void save(Data_weather data_weather);
    void save (Data_people data_people);

    void delete(Data_dim data_dim);
    void delete(Data_traffic data_traffic);
    void delete(Data_weather data_weather);
    void delete(Data_people data_people);
}
