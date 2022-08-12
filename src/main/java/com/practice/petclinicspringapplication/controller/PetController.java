package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.IPetService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PetController
{
    private final IPetService petService;
    private final OwnerRepo ownerRepo;

    //Constructor
    public PetController(IPetService petService, OwnerRepo ownerRepo) {
        this.petService = petService;
        this.ownerRepo = ownerRepo;
    }

    //Methods
    //Add pet
    @PostMapping("/pets")
    public void add(@RequestParam Optional<Long> id, @RequestParam(required = false) Long ownerId, @RequestBody Pet pet)
    {
       if(id.isPresent())
       {
           throw new UpdateObjectInPostException();
       }
       pet.setOwner(ownerRepo.findById(ownerId).orElseThrow(()-> new OwnerNotFoundException(ownerId)));
        petService.add(pet);
    }

    //Find pet by id
    @GetMapping("/pets/{id}")
    public Pet findVet(@PathVariable Long id)
    {
        return petService.findById(id);
    }

    //See all pets
    @GetMapping("/pets")
    public Iterable<Pet> getPets() {
        return petService.findAll();
    }

    //Count all pets
    @GetMapping("/pets/count")
    public Long count()
    {
        return petService.count();
    }

    //Update a pet
    @PutMapping("/pets/{id}")
    public void update(@PathVariable Long id, @RequestParam(required = false) Long ownerId, @RequestBody Pet pet)
    {
        petService.update(id, pet, ownerId);
    }

    //delete pet by id
    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable Long id)
    {
        petService.deleteById(id);
    }
}
