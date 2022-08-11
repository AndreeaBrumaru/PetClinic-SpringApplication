package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Owner;

import java.util.List;

public interface IOwnerService {
    void add(Owner newOwner);
    Owner findById(Long idOwner);
    List<Owner> findAll();
    Long count();
    void update(Long id, String firstName, String lastName);
    void deleteById(Long idOwner);
}
