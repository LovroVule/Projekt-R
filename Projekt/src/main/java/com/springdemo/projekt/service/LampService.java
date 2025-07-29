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

    void deleteByAddres(String addres);
}
