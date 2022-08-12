package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IVetService;
import org.springframework.web.bind.annotation.*;

@RestController
public class VetController {

    private final IVetService vetService;

    //Constructor
    public VetController(IVetService vetService) {
        this.vetService = vetService;
    }

    //Methods
    //Add vet
    @PostMapping(value="/vets")
    public void addVet(@RequestBody Vet vet)
    {
        vetService.add(vet);
    }

    //Find vet by id
    @GetMapping("/vets/{id}")
    public Vet findVet(@PathVariable Long id)
    {
        return vetService.findById(id);
    }

    //See all vets
    @GetMapping("/vets")
    public Iterable<Vet> getVets() {
        return vetService.findAll();
    }

    //Count all vets
    @GetMapping("/vets/count")
    public Long count()
    {
        return vetService.count();
    }

    //Update a vet
    @PutMapping("/vets/{id}")
    public void update(@PathVariable Long id, @RequestBody Vet vet)
    {
        vetService.update(id, vet);
    }

    //delete vet by id
    @DeleteMapping("vets/{id}")
    public void delete(@PathVariable Long id)
    {
        vetService.deleteById(id);
    }
}
