package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.PetRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepo petRepo;

    //Constructor
    public PetService(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    //Methods
    //Add new pet
    public void add(Pet newPet)
    {
        petRepo.save(newPet);
        System.out.println("Added new pet: " + newPet);
    }

    //Find pet by id
    public Optional<Pet> findById(Long idPet)
    {
        return petRepo.findById(idPet);
    }

    //Find all pets
    public List<Pet> findAll()
    {
        var it = petRepo.findAll();
        List<Pet> pets = new ArrayList<>();
        it.forEach(pets::add);
        return pets;
    }

    //Count all pets
    public Long count()
    {
        return petRepo.count();
    }

    //update pet by id
    //TODO Implement pet update method

    //delete vet by id
    public void deleteById(Long idPet)
    {
        System.out.println("Deleting: " + petRepo.findById(idPet));
        petRepo.deleteById(idPet);
    }
}
