package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.VetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VetController {

    private final VetService vetService;

    //Constructor
    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    //Methods
    //Add vet
    @PostMapping("vets/add")
    public void add(@RequestParam String firstName, @RequestParam String lastName)
    {
        Vet newVet = new Vet(firstName, lastName);
        vetService.add(newVet);
    }

    //Find vet by id
    @GetMapping("vets/{id}")
    public Optional<Vet> findVet(@PathVariable String id)
    {
        Long vetId = Long.parseLong(id);
        return vetService.findById(vetId);
    }

    //See all vets
    @GetMapping("/vets")
    public ResponseEntity getVets() {
        return ResponseEntity.ok(vetService.findAll());
    }

    //Count all vets
    @GetMapping("/vets/count")
    public Long count()
    {
        return vetService.count();
    }

    //Update a vet
    //TODO implement update vet method

    //delete vet by id
    @DeleteMapping("vets/delete/{id}")
    public void delete(@PathVariable String id)
    {
        Long vetId = Long.parseLong(id);
        vetService.deleteById(vetId);
    }
}
