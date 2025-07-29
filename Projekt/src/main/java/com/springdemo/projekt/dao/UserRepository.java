package com.springdemo.projekt.dao;

import com.springdemo.projekt.domain.MyUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);

    @Modifying               // označava DML operaciju
    @Transactional
    void deleteByUsername(String username);
}
