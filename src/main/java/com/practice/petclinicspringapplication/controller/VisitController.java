package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.exception.VetNotFoundException;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.service.IVisitService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
public class VisitController {

    private final IVisitService visitService;
    private final OwnerRepo ownerRepo;
    private final PetRepo petRepo;
    private final VetRepo vetRepo;

    //Constructor
    public VisitController(IVisitService visitService, OwnerRepo ownerRepo, PetRepo petRepo, VetRepo vetRepo) {
        this.visitService = visitService;
        this.ownerRepo = ownerRepo;
        this.petRepo = petRepo;
        this.vetRepo = vetRepo;
    }

    //Methods
    //Add visit
    @PostMapping("/visits")
    public void add(@RequestParam Optional<Long> id, @RequestParam Long petId, @RequestParam Long vetId, @RequestBody Visit visit)
    {
        if(id.isPresent())
        {
            throw new UpdateObjectInPostException();
        }
        visit.setDateOfVisit(LocalDate.now());
        visit.setPet(petRepo.findById(petId).orElseThrow(() -> new PetNotFoundException(petId)));
        visit.setVet(vetRepo.findById(vetId).orElseThrow(() -> new VetNotFoundException(vetId)));
        visitService.add(visit);
    }

    //Find visit by id
    @GetMapping("/visits/{id}")
    public Visit findVisit(@PathVariable Long id)
    {
        return visitService.findById(id);
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

    //Update a visit
    //TODO Make stuff optional
    @PutMapping("/visits/{id}")
    public void update(@PathVariable Long id, @RequestParam Long petId, @RequestParam Long vetId, @RequestBody Visit visit)
    {
        visitService.update(id, visit, petId, vetId);
    }

    //delete visit by id
    @DeleteMapping("/visits/{id}")
    public void delete(@PathVariable Long id)
    {
        visitService.deleteById(id);
    }
}
