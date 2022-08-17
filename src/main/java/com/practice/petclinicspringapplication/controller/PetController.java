package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.IPetService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import java.text.ParseException;
import java.util.Optional;

@RestController
public class PetController
{
    private final IPetService petService;
    private final OwnerRepo ownerRepo;
    private final ModelMapper modelMapper;

    //Constructor
    public PetController(IPetService petService, OwnerRepo ownerRepo, ModelMapper modelMapper) {
        this.petService = petService;
        this.ownerRepo = ownerRepo;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add pet
    @PostMapping("/pets")
    public void add(@RequestParam Optional<Long> id, @RequestParam Long ownerId, @RequestBody PetDto petDto) throws ParseException {
       if(id.isPresent())
       {
           throw new UpdateObjectInPostException();
       }
       Pet pet = convertToEntity(petDto);
       pet.setOwner(ownerRepo.findById(ownerId).orElseThrow(()-> new OwnerNotFoundException(ownerId)));
       petService.add(pet);
    }

    //Find pet by id
    @GetMapping("/pets/{id}")
    public PetDto findVet(@PathVariable Long id)
    {
        //TODO Make it show all visits of pet as well
        return petService.findById(id);
    }

    //See all pets
    @GetMapping("/pets")
    public Iterable<PetDto> getPets() {
        return petService.findAll();
    }

    //Find pets by owner
    @GetMapping("/pets/owner/{ownerId}")
    public Iterable<PetDto> getPetsByOwner(@PathVariable Long ownerId)
    {
        return petService.findByOwner(ownerId);
    }

    //Count all pets
    @GetMapping("/pets/count")
    public Long count()
    {
        return petService.count();
    }

    //Update a pet
    @PutMapping("/pets/{id}")
    public void update(@PathVariable Long id, @RequestParam Long ownerId, @RequestBody PetDto petDto) throws ParseException {
        Pet pet = convertToEntity(petDto);
        petService.update(id, pet, ownerId);
    }

    //delete pet by id
    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable Long id)
    {
        petService.deleteById(id);
    }

    //Convert Dto to entity
    @Transient
    private Pet convertToEntity(PetDto petDto) throws ParseException
    {
        return modelMapper.map(petDto, Pet.class);
    }
}
