package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepo ownerRepo;
    private final PetRepo petRepo;

    //Constructor
    public OwnerService(OwnerRepo ownerRepo, PetRepo petRepo) {
        this.ownerRepo = ownerRepo;
        this.petRepo = petRepo;
    }

    //Methods
    //Add new owner
    public void add(Owner newOwner)
    {
        ownerRepo.save(newOwner);
        System.out.println("Added new owner: " + newOwner);
    }

    //Add pet to owner
    //TODO Implement adding pet to owner
//    public void addPet(Long ownerId, Long petId)
//    {
//        Optional<Owner> owner = ownerRepo.findById(ownerId);
//        Optional<Pet> pet = petRepo.findById(petId);
//        (Owner) owner.getPets().add(pet);
//        (Pet) pet.setOwner(owner);
//        ownerRepo.save(owner);
//        petRepo.save(pet);
//    }

    //Find owner by id
    public Optional<Owner> findById(Long idOwner)
    {
        return ownerRepo.findById(idOwner);
    }

    //Find all owners
    public List<Owner> findAll()
    {
        var it = ownerRepo.findAll();
        List<Owner> owners = new ArrayList<>();
        it.forEach(owners::add);
        return owners;
    }

    //Count all owner
    public Long count()
    {
        return ownerRepo.count();
    }

    //update owner by id
    //TODO Implement owner update method

    //delete owner by id
    public void deleteById(Long idOwner)
    {
        System.out.println("Deleting: " + ownerRepo.findById(idOwner));
        ownerRepo.deleteById(idOwner);
    }

}
