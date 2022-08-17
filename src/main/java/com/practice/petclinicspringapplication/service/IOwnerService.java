package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.OwnerDto;
import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Owner;

import java.util.List;

public interface IOwnerService {
    void add(Owner newOwner);
    OwnerDto findById(Long idOwner);
    List<OwnerDto> findAll();

    //Show all of the owner's pets
    List<PetDto> getPets(Long ownerId);

    //Show all the owner's visits
    List<VisitDto> getVisits(Long ownerId);

    Long count();
    void update(Long id, Owner owner);
    void deleteById(Long idOwner);
}
