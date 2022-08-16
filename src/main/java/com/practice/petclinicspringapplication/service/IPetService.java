package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Pet;

import java.util.List;

public interface IPetService {
    void add(Pet newPet);
    Pet findById(Long idPet);
    List<Pet> findAll();
    Long count();
    void update(Long id, Pet pet, Long ownerId);

    void deleteById(Long idPet);
    List<Pet> findByOwner(Long ownerId);
}
