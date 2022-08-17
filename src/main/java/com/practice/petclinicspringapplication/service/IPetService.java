package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Pet;

import java.util.List;

public interface IPetService {
    void add(Pet newPet);
    PetDto findById(Long idPet);
    List<PetDto> findAll();

    //See all the pets visits
    List<VisitDto> findPetsVisits(Long petId);

    Long count();
    void update(Long id, Pet pet, Long ownerId);
    void update(Long id, Pet pet);
    void deleteById(Long idPet);
    List<PetDto> findByOwner(Long ownerId);
}
