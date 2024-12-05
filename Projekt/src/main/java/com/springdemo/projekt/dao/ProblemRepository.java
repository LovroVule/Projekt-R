package com.springdemo.projekt.dao;

import com.springdemo.projekt.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Long> {
}
