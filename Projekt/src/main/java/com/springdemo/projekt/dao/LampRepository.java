package com.springdemo.projekt.dao;

import com.springdemo.projekt.domain.Lamp;
import com.springdemo.projekt.domain.Problem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface LampRepository extends JpaRepository<Lamp, Long> {

    @Modifying
    @Transactional
    void deleteByAddres(String addres);
}
