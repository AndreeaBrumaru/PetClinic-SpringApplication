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
    //TODO Make it so that it also accepts id and if it exists throw custom exception
    @PostMapping("/owners")
    public void add(@RequestParam Optional<String> id, @RequestParam String firstName, @RequestParam String lastName)
    {
        if(id.isEmpty())
        {
            Owner newOwner = new Owner(firstName, lastName);
            ownerService.add(newOwner);
        }
        else {
            System.out.println("You are trying to update an owner. Please use the put method.");
        }
    }

    //Find owner by id
    @GetMapping("/owners/{id}")
    public Owner findOwner(@PathVariable String id)
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
    //TODO make it throw exception if owner doesn't exist
    @PutMapping("/owners")
    public void update(@RequestParam String id, @RequestParam String firstName, @RequestParam String lastName)
    {
        ownerService.update(Long.parseLong(id), firstName, lastName);
    }

    //delete owner by id
    @DeleteMapping("/owners/{id}")
    public void delete(@PathVariable String id)
    {
        ownerService.deleteById(Long.parseLong(id));
    }
}
