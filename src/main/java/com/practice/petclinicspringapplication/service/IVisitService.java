package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Visit;

import java.util.List;

public interface IVisitService {
    void add(Visit visit);
    Visit findById(Long idVisit);
    List<Visit> findAll();
    Long count();
    void update(Long id, Visit visit, Long petId, Long vetId);

    void deleteById(Long idVisit);
}
