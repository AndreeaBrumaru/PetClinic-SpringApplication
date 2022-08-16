package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.IPetService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public PetDto findVet(@PathVariable Long id)
    {
        return convertToDto(petService.findById(id));
    }

    //See all pets
    @GetMapping("/pets")
    public Iterable<PetDto> getPets() {
        List<Pet> pets = petService.findAll();
        return pets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Find pets by owner
    @GetMapping("/pets/owner/{ownerId}")
    public Iterable<PetDto> getPetsByOwner(@PathVariable Long ownerId)
    {
        List<Pet> pets = petService.findByOwner(ownerId);
        return pets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all pets
    @GetMapping("/pets/count")
    public Long count()
    {
        return petService.count();
    }

    //Update a pet
    @PutMapping("/pets/{id}")
    public void update(@PathVariable Long id, @RequestParam Long ownerId, @RequestBody Pet pet)
    {
        petService.update(id, pet, ownerId);
    }

    //delete pet by id
    @DeleteMapping("/pets/{id}")
    public void delete(@PathVariable Long id)
    {
        petService.deleteById(id);
    }

    //Convert Entity to dto
    private PetDto convertToDto(Pet pet)
    {
        return modelMapper.map(pet, PetDto.class);
    }

    //Convert Dto to entity
    private Pet convertToEntity(PetDto petDto) throws ParseException
    {
        Pet pet = modelMapper.map(petDto, Pet.class);
        if(petDto.getId() != null)
        {
            Pet oldPet = petService.findById(petDto.getId());
            pet.setIdPet(oldPet.getIdPet());
        }
        return pet;
    }
}
