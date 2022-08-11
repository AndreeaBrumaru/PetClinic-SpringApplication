package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.IPetService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    //TODO make birthday be optional
    @PostMapping("/pets")
    public void add(@RequestParam String name, @RequestParam String type, @RequestParam String birthDate, @RequestParam String owner_id, @RequestParam Optional<String> id)
    {
       if(id.isPresent())
       {
           throw new UpdateObjectInPostException();
       }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate birth = LocalDate.parse(birthDate, formatter);

        Pet newPet = new Pet(name, birth, type, ownerRepo.findById(Long.parseLong(owner_id)).orElseThrow(() -> new OwnerNotFoundException(Long.parseLong(owner_id))));
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
