package com.springdemo.projekt.dao;

import com.springdemo.projekt.domain.Data_people;
import com.springdemo.projekt.domain.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Data_people_Repository extends JpaRepository<Data_people, Long> {
}
