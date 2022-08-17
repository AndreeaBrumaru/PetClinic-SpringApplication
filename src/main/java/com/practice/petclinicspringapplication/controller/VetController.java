package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IVetService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;

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
    @PostMapping(value="/vets")
    public void addVet(@RequestBody VetDto vetDto)
    {
        Vet vet = convertToEntity(vetDto);
        vetService.add(vet);
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
    public Long count()
    {
        return vetService.count();
    }

    //Update a vet
    @PutMapping("/vets/{id}")
    public void update(@PathVariable Long id, @RequestBody VetDto vetDto)
    {
        Vet vet = convertToEntity(vetDto);
        vetService.update(id, vet);
    }

    //delete vet by id
    @DeleteMapping("vets/{id}")
    public void delete(@PathVariable Long id)
    {
        vetService.deleteById(id);
    }

    //Convert DTO to entity
    @Transient
    private Vet convertToEntity(VetDto vetDto)
    {
        return modelMapper.map(vetDto, Vet.class);
    }
}
