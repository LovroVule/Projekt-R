package com.springdemo.projekt.dao;

import com.springdemo.projekt.domain.Data_dim;
import com.springdemo.projekt.domain.Lamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Data_dim_Repository extends JpaRepository<Data_dim, Long> {
}
