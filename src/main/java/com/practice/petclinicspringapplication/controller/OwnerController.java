package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.service.IOwnerService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class OwnerController {
    private final IOwnerService ownerService;

    //Constructor
    public OwnerController(IOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //Methods
    //Add owner
    @PostMapping("/owners")
    public void add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Optional<String> id)
    {
        if(id.isPresent())
        {
            //Throws exception if id has been inserted
            throw new UpdateObjectInPostException();
        }
        Owner newOwner = new Owner(firstName, lastName);
        ownerService.add(newOwner);
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
