package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IVetService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;

@RestController
public class VetController {

    private final IVetService vetService;
    private final ModelMapper modelMapper;

    //Constructor
    public VetController(IVetService vetService, ModelMapper modelMapper) {
        this.vetService = vetService;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add vet
    @PostMapping("/vets")
    public ResponseEntity<String> addVet(@Valid @RequestBody VetDto vetDto)
    {
        Vet vet = convertToEntity(vetDto);
        vetService.add(vet);
        return ResponseEntity.ok("New vet added");
    }

    //Find vet by id
    @GetMapping("/vets/{id}")
    public VetDto findVet(@PathVariable Long id)
    {
        //TODO Make it show all visits that vet had to do
        return vetService.findById(id);
    }

    //See all vets
    @GetMapping("/vets")
    public Iterable<VetDto> getVets() {
        return vetService.findAll();
    }

    //Count all vets
    @GetMapping("/vets/count")
    public String count()
    {
        return "There are " + vetService.count() + " vets registered in the database";
    }

    //Update a vet
    @PutMapping("/vets/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@Valid @RequestBody VetDto vetDto)
    {
        Vet vet = convertToEntity(vetDto);
        vetService.update(id, vet);
        return ResponseEntity.ok("Vet updated");
    }

    //delete vet by id
    @DeleteMapping("vets/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        vetService.deleteById(id);
        return ResponseEntity.ok("Vet deleted");
    }

    //Convert DTO to entity
    @Transient
    private Vet convertToEntity(VetDto vetDto)
    {
        return modelMapper.map(vetDto, Vet.class);
    }
}
