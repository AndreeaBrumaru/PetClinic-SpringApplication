package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void update(Long id, String name, String type, LocalDate birthDate, Long owner_id)
    {
        Pet pet = findById(id);
        pet.setNamePet(name);
        pet.setPetType(type);
        pet.setBirthDate(birthDate);
        pet.setOwner(ownerRepo.findById(owner_id).orElseThrow(() -> new OwnerNotFoundException(owner_id)));
        petRepo.save(pet);
    }

    //delete vet by id
    @Override
    public void deleteById(Long idPet)
    {
        System.out.println("Deleting: " + petRepo.findById(idPet));
        petRepo.deleteById(idPet);
    }
}
