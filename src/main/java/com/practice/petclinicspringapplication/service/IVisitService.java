package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Visit;

import java.util.List;

public interface IVisitService {
    void add(Visit visit);
    VisitDto findById(Long idVisit);
    List<VisitDto> findAll();
    List<VisitDto> findByVet(Long vetId);
    List<VisitDto> findByPet(Long petId);
    Long count();
    void update(Long id, Visit visit, Long petId, Long vetId);
    void update(Long id, Visit visit);
    void deleteById(Long idVisit);
}
