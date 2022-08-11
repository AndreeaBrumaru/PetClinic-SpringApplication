package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class PetController
{
    private final PetService petService;
    private final OwnerRepo ownerRepo;

    //Constructor
    public PetController(PetService petService, OwnerRepo ownerRepo) {
        this.petService = petService;
        this.ownerRepo = ownerRepo;
    }

    //Methods
    //Add pet
    //TODO Make throw error if id is put(make birthdate be nullable as well
    @PostMapping("/pets")
    public void add(@RequestParam String name, @RequestParam String type, @RequestParam String birthDate, @RequestParam String owner_id) throws RuntimeException
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birth = LocalDate.parse(birthDate, formatter);

        Pet newPet = new Pet(name, birth, type, ownerRepo.findById(Long.parseLong(owner_id)).orElseThrow(RuntimeException::new));
        petService.add(newPet);
    }

    //Find pet by id
    @GetMapping("/pets/{id}")
    public Pet findVet(@PathVariable String id)
    {
        return petService.findById(Long.parseLong(id));
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
    //TODO make it throw exception if owner doesn't exist
    @PutMapping("/pets")
    public void update(@RequestParam String petId, @RequestParam String name,@RequestParam String type, @RequestParam String birthDate, @RequestParam String owner_id)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birth = LocalDate.parse(birthDate, formatter);
        petService.update(Long.parseLong(petId), name, type, birth, Long.parseLong(owner_id));
    }

    //delete pet by id
    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable String id)
    {
        Long petId = Long.parseLong(id);
        petService.deleteById(petId);
    }
}
