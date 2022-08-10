package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PetController
{
    private final PetService petService;

    //Constructor
    public PetController(PetService petService) {
        this.petService = petService;
    }

    //Methods
    //Add pet
    //TODO cum adaug localdate ca parametru
//    @PostMapping("pets/add")
//    public void add(@RequestParam String name, @RequestParam String type, @RequestParam LocalDate birthDate)
//    {
//        Pet newPet = new Pet(name, birthDate, type);
//        petService.add(newPet);
//    }

    @PostMapping("pets/add")
    public void add(@RequestParam String name, @RequestParam String type)
    {
        Pet newPet = new Pet(name, type);
        petService.add(newPet);
    }

    //Find pet by id
    @GetMapping("pets/{id}")
    public Optional<Pet> findVet(@PathVariable String id)
    {
        Long petId = Long.parseLong(id);
        return petService.findById(petId);
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
    //TODO implement update vet method

    //delete pet by id
    @DeleteMapping("pets/delete/{id}")
    public void delete(@PathVariable String id)
    {
        Long petId = Long.parseLong(id);
        petService.deleteById(petId);
    }
}
