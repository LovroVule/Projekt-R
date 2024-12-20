package com.springdemo.projekt.service.impl;

import com.springdemo.projekt.dao.ProblemRepository;
import com.springdemo.projekt.domain.Problem;
import com.springdemo.projekt.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProblemServiceJpa implements ProblemService {

    @Autowired
    private ProblemRepository problemRepository;


    @Override
    public List<Problem> listAll() {
        return problemRepository.findAll();
    }

    @Override
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    @Override
    public Problem findProblemByAdress(String adress) {
        List<Problem> allProblems = problemRepository.findAll();
        for (Problem problem : allProblems) {
            if (problem.getAdress().equals(adress)) {
                return problem;
            }
        }
        return null;
    }

    @Override
    public List<Problem> findNotWorking() {
        return problemRepository.findAll().stream().filter(problem -> Objects.equals(problem.getStatus(), "0")).collect(Collectors.toList());
    }

    @Override
    public List<Problem> findWorking() {
        return problemRepository.findAll().stream().filter(problem -> Objects.equals(problem.getStatus(), "1")).collect(Collectors.toList());
    }
}
