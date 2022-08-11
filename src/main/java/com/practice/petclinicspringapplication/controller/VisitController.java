package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class VisitController {

    private final VisitService visitService;
    private final OwnerRepo ownerRepo;
    private final PetRepo petRepo;
    private final VetRepo vetRepo;

    //Constructor
    public VisitController(VisitService visitService, OwnerRepo ownerRepo, PetRepo petRepo, VetRepo vetRepo) {
        this.visitService = visitService;
        this.ownerRepo = ownerRepo;
        this.petRepo = petRepo;
        this.vetRepo = vetRepo;
    }

    //Methods
    //Add visit
    //TODO throw exception if exists
    @PostMapping("/visits")
    public void add(@RequestParam String reasonForVisit, @RequestParam String ownerId, @RequestParam String petId, @RequestParam String vetId) throws RuntimeException
    {
        Visit visit = new Visit(reasonForVisit, LocalDate.now(),
                ownerRepo.findById(Long.parseLong(ownerId)).orElseThrow(RuntimeException::new),
                petRepo.findById(Long.parseLong(petId)).orElseThrow(RuntimeException::new),
                vetRepo.findById(Long.parseLong(vetId)).orElseThrow(RuntimeException::new));
        visitService.add(visit);
    }

    //Find visit by id
    //TODO Throw custom exception if owner exists(make it also accept id)
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
    //TODO Make it throw exception if doesn't exist(make stuff nullable)
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
