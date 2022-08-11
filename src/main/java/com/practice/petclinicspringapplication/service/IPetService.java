package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Pet;

import java.time.LocalDate;
import java.util.List;

public interface IPetService {
    void add(Pet newPet);
    Pet findById(Long idPet);
    List<Pet> findAll();
    Long count();
    void update(Long id, String name, String type, LocalDate birthDate, Long owner_id);
    void deleteById(Long idPet);
}
