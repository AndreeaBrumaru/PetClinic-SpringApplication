package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.IPetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;
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
    public ResponseEntity<String> add(@RequestParam Optional<Long> id, @RequestParam(required = false) Long ownerId, @Valid @RequestBody PetDto petDto) throws ParseException {
       if(id.isPresent())
       {
           throw new UpdateObjectInPostException();
       }
       Pet pet = convertToEntity(petDto);
       if(ownerId != null) {
           pet.setOwner(ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(ownerId)));
       }
       petService.add(pet);
       return ResponseEntity.ok("New pet added.");
    }

    //Find pet by id
    @GetMapping("/pets/{id}")
    public PetDto findVet(@PathVariable Long id)
    {
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

    //See all the pets visits
    @GetMapping("/pets/{id}/visits")
    public Iterable<VisitDto> getPetsVisits(@PathVariable Long id)
    {
        return petService.findPetsVisits(id);
    }

    //Count all pets
    @GetMapping("/pets/count")
    public String count()
    {
        return "There are " + petService.count() + " pets registered in the database.";
    }

    //Update a pet
    @PutMapping("/pets/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestParam(required = false) Long ownerId, @Valid @RequestBody PetDto petDto) throws ParseException {
        Pet pet = convertToEntity(petDto);
        if(ownerId != null)
        {
            petService.update(id, pet, ownerId);
            return ResponseEntity.ok("Pet updated.");
        }
        petService.update(id, pet);
        return ResponseEntity.ok("Pet updated.");
    }

    //delete pet by id
    @DeleteMapping("/pets/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        petService.deleteById(id);
        return ResponseEntity.ok("Pet deleted.");
    }

    //Convert Dto to entity
    @Transient
    private Pet convertToEntity(PetDto petDto) throws ParseException
    {
        return modelMapper.map(petDto, Pet.class);
    }
}
