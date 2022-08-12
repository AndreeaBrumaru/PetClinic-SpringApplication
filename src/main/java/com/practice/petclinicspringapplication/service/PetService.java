package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetService implements IPetService{
    private final PetRepo petRepo;
    private final OwnerRepo ownerRepo;

    //Constructor
    public PetService(PetRepo petRepo, OwnerRepo ownerRepo) {
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
    }

    //Methods
    //Add new pet
    @Override
    public void add(Pet newPet)
    {
        petRepo.save(newPet);
        System.out.println("Added new pet: " + newPet);
    }

    //Find pet by id
    @Override
    public Pet findById(Long idPet) throws RuntimeException
    {
        return petRepo.findById(idPet).orElseThrow(() -> new PetNotFoundException(idPet));
    }

    //Find all pets
    @Override
    public List<Pet> findAll()
    {
        var it = petRepo.findAll();
        List<Pet> pets = new ArrayList<>();
        it.forEach(pets::add);
        if(pets.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return pets;
    }

    //Count all pets
    @Override
    public Long count()
    {
        return petRepo.count();
    }

    //update pet by id
    @Override
    public void update(Long id, Pet pet, Long ownerId)
    {
        Pet oldPet = findById(id);
        oldPet.setNamePet(pet.getNamePet());
        oldPet.setPetType(pet.getPetType());
        oldPet.setBirthDate(pet.getBirthDate());
        //TODO Continue this on controller
        oldPet.setOwner(ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(ownerId)));
        petRepo.save(oldPet);
    }

    //delete vet by id
    @Override
    public void deleteById(Long idPet)
    {
        //TODO Deleting pet also deletes owner by association
        System.out.println("Deleting: " + petRepo.findById(idPet));
        petRepo.deleteById(idPet);
    }
}
