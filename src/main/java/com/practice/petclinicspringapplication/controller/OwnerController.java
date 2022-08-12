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
    public void add(@RequestParam Optional<Long> id, @RequestBody Owner owner)
    {
        if(id.isPresent())
        {
            //Throws exception if id has been inserted
            throw new UpdateObjectInPostException();
        }
        ownerService.add(owner);
    }

    //Find owner by id
    @GetMapping("/owners/{id}")
    public Owner findOwner(@PathVariable Long id)
    {
        return ownerService.findById(id);
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
    @PutMapping("/owners/{id}")
    public void update(@PathVariable Long id, @RequestBody Owner owner)
    {
        ownerService.update(id, owner);
    }

    //delete owner by id
    @DeleteMapping("/owners/{id}")
    public void delete(@PathVariable Long id)
    {
        ownerService.deleteById(id);
    }
}
