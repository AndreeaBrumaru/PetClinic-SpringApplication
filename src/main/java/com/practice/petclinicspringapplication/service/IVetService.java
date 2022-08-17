package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Vet;

import java.util.List;

public interface IVetService {
    void add(Vet newVet);
    VetDto findById(Long idVet);
    List<VetDto> findAll();

    //See oll of the vets visits
    List<VisitDto> findAllVisits(Long id);

    //See all the pets the vet has taken care of
    List<PetDto> findAllPets(Long id);

    Long count();
    void update(Long id, Vet vet);
    void deleteById(Long idVet);
}
