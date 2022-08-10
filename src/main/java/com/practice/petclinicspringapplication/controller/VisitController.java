package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.service.OwnerService;
import com.practice.petclinicspringapplication.service.PetService;
import com.practice.petclinicspringapplication.service.VetService;
import com.practice.petclinicspringapplication.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VisitController {

    private final VetService vetService;
    private final VisitService visitService;
    private final OwnerService ownerService;
    private final PetService petService;

    //Constructor
    public VisitController(VetService vetService, VisitService visitService, OwnerService ownerService, PetService petService) {
        this.vetService = vetService;
        this.visitService = visitService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    //Methods
    //Add visit
    //TODO Add visit
    @PostMapping("visit/add")
    public void add(@RequestParam String reasonForVisit, @RequestParam String ownerId, @RequestParam String petId, @RequestParam String vetId)
    {
        Optional<Owner> owner = ownerService.findById(Long.parseLong(ownerId));
        Optional<Pet> pet = petService.findById(Long.parseLong(petId));
        Optional<Vet> vet = vetService.findById(Long.parseLong(vetId));
        //Visit visit = new Visit(reasonForVisit, owner, pet, vet);
    }

    //Find visit by id
    @GetMapping("visit/{id}")
    public Optional<Visit> findVisit(@PathVariable String id)
    {
        return visitService.findById(Long.parseLong(id));
    }

    //See all visits
    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitService.findAll();
    }

    //Count all visits
    @GetMapping("/visits/count")
    public Long count()
    {
        return visitService.count();
    }

    //Update a vet
    //TODO implement update visit method

    //delete visit by id
    @DeleteMapping("visits/delete/{id}")
    public void delete(@PathVariable String id)
    {
        visitService.deleteById(Long.parseLong(id));
    }
}
