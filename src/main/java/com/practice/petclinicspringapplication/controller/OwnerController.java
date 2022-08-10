package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.service.OwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OwnerController {
    private final OwnerService ownerService;

    //Constructor
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //Methods
    //Add owner
    @PostMapping("owners/add")
    public void add(@RequestParam String firstName, @RequestParam String lastName)
    {
        Owner newOwner = new Owner(firstName, lastName);
        ownerService.add(newOwner);
    }

    //Add pet to owner
    //TODO Implement add pet to owner
//    @PostMapping("/owners/addPet")
//    public void addPet(@RequestParam String ownerId, @RequestParam String petId)
//    {
//        Long oId = Long.parseLong(ownerId);
//        Long pId = Long.parseLong(petId);
//        ownerService.addPet(oId, pId);
//    }

    //Find owner by id
    @GetMapping("owners/{id}")
    public Optional<Owner> findOwner(@PathVariable String id)
    {
        Long ownerId = Long.parseLong(id);
        return ownerService.findById(ownerId);
    }

    //See all owners
    @GetMapping("/owners")
    public Iterable<Owner> getOwners() {
        return ownerService.findAll();
    }

    //Count all owners
    @GetMapping("/owners/count")
    public Long count()
    {
        return ownerService.count();
    }

    //Update an owner
    //TODO implement update vet method

    //delete owner by id
    @DeleteMapping("owners/delete/{id}")
    public void delete(@PathVariable String id)
    {
        Long ownerId = Long.parseLong(id);
        ownerService.deleteById(ownerId);
    }
}
