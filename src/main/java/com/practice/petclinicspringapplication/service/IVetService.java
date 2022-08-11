package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Vet;

import java.util.List;

public interface IVetService {
    void add(Vet newVet);
    Vet findById(Long idVet);
    List<Vet> findAll();
    Long count();
    void update(Long id, String firstName, String lastName);
    void deleteById(Long idVet);
}
