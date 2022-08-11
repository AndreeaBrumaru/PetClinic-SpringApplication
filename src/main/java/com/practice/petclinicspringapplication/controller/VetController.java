package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IVetService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class VetController {

    private final IVetService vetService;

    //Constructor
    public VetController(IVetService vetService) {
        this.vetService = vetService;
    }

    //Methods
    //Add vet
    @PostMapping("/vets")
    public void add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam Optional<String> id)
    {
        if(id.isPresent())
        {
            throw new UpdateObjectInPostException();
        }
        Vet newVet = new Vet(firstName, lastName);
        vetService.add(newVet);
    }

    //Find vet by id
    @GetMapping("/vets/{id}")
    public Vet findVet(@PathVariable String id)
    {
        Long vetId = Long.parseLong(id);
        return vetService.findById(vetId);
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
    @PutMapping("/vets")
    public void update(@RequestParam String vetId, @RequestParam String firstName,@RequestParam String lastName)
    {
        vetService.update(Long.parseLong(vetId), firstName, lastName);
    }

    //delete vet by id
    @DeleteMapping("vets/{id}")
    public void delete(@PathVariable String id)
    {
        vetService.deleteById(Long.parseLong(id));
    }
}
