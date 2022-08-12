package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Owner;

import java.util.List;

public interface IOwnerService {
    void add(Owner newOwner);
    Owner findById(Long idOwner);
    List<Owner> findAll();
    Long count();
    void update(Long id, Owner owner);
    void deleteById(Long idOwner);
}
