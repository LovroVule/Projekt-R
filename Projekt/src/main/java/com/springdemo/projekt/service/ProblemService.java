package com.springdemo.projekt.service;

import com.springdemo.projekt.domain.Problem;

import java.util.List;

public interface ProblemService {
    List<Problem> listAll();
    Problem createProblem(Problem problem);

}
