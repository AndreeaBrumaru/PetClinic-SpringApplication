package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.model.Vet;

import java.util.List;

public interface IVetService {
    void add(Vet newVet);
    VetDto findById(Long idVet);
    List<VetDto> findAll();
    Long count();
    void update(Long id, Vet vet);
    void deleteById(Long idVet);
}
