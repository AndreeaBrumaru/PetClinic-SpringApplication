package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
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
import java.time.format.DateTimeFormatter;
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
    public void add(@RequestParam String reasonForVisit, @RequestParam String ownerId, @RequestParam String petId, @RequestParam String vetId, @RequestParam Optional<String> id)
    {
        if(id.isPresent())
        {
            throw new UpdateObjectInPostException();
        }
        Visit visit = new Visit(reasonForVisit, LocalDate.now(),
                ownerRepo.findById(Long.parseLong(ownerId)).orElseThrow(() -> new OwnerNotFoundException(Long.parseLong(ownerId))),
                petRepo.findById(Long.parseLong(petId)).orElseThrow(() -> new PetNotFoundException(Long.parseLong(petId))),
                vetRepo.findById(Long.parseLong(vetId)).orElseThrow(() -> new VetNotFoundException(Long.parseLong(vetId))));
        visitService.add(visit);
    }

    //Find visit by id
    @GetMapping("/visits/{id}")
    public Visit findVisit(@PathVariable String id)
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

    //Update a visit
    //TODO Make stuff optional
    @PutMapping("/visits")
    public void update(@RequestParam String id, @RequestParam String reason, @RequestParam String date, @RequestParam String ownerId, @RequestParam String petId, @RequestParam String vetId)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate newDate = LocalDate.parse(date, formatter);
        visitService.update(Long.parseLong(id), reason, newDate, Long.parseLong(ownerId), Long.parseLong(petId), Long.parseLong(vetId));
    }

    //delete visit by id
    @DeleteMapping("/visits/{id}")
    public void delete(@PathVariable String id)
    {
        visitService.deleteById(Long.parseLong(id));
    }
}
