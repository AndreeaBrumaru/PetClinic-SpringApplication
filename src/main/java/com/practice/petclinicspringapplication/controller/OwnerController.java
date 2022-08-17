package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.OwnerDto;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.service.IOwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@RestController
public class OwnerController {
    private final IOwnerService ownerService;
    private final ModelMapper modelMapper;

    //Constructor
    public OwnerController(IOwnerService ownerService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add owner
    @PostMapping("/owners")
    public ResponseEntity<String> add(@RequestParam Optional<Long> id, @Valid @RequestBody OwnerDto ownerdto) throws ParseException {
        if(id.isPresent())
        {
            //Throws exception if id has been inserted
            throw new UpdateObjectInPostException();
        }
        Owner owner = convertToEntity(ownerdto);
        ownerService.add(owner);
        return ResponseEntity.ok("New owner added.");
    }

    //Find owner by id
    @GetMapping("/owners/{id}")
    public OwnerDto findOwner(@PathVariable Long id)
    {
        //TODO Make it show all pets of owner as well
        return ownerService.findById(id);
    }

    //See all owners
    @GetMapping("/owners")
    public Iterable<OwnerDto> getOwners() {
        return ownerService.findAll();
    }

    //Count all owners
    @GetMapping("/owners/count")
    public String count()
    {
        return "There are " + ownerService.count() + " owners registered on the database.";
    }

    //Update an owner
    @PutMapping("/owners/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody OwnerDto ownerDto) throws ParseException {
        Owner owner = convertToEntity(ownerDto);
        ownerService.update(id, owner);
        return ResponseEntity.ok("Owner updated.");
    }

    //delete owner by id
    @DeleteMapping("/owners/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        ownerService.deleteById(id);
        return ResponseEntity.ok("Owner deleted.");
    }

    //convert Dto to entity
    @Transient
    private Owner convertToEntity(OwnerDto ownerDto) throws ParseException
    {
        return modelMapper.map(ownerDto, Owner.class);
    }
}
