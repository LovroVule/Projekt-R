package com.springdemo.projekt.dao;

import com.springdemo.projekt.domain.Data_weather;
import com.springdemo.projekt.domain.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Data_weather_Repository extends JpaRepository<Data_weather, Long> {
}
