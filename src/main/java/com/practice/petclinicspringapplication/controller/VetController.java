package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IVetService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public void addVet(@RequestBody Vet vet)
    {
        vetService.add(vet);
    }

    //Find vet by id
    @GetMapping("/vets/{id}")
    public VetDto findVet(@PathVariable Long id)
    {
        return convertToDto(vetService.findById(id));
    }

    //See all vets
    @GetMapping("/vets")
    public Iterable<VetDto> getVets() {
        List<Vet> vets = vetService.findAll();
        return vets.stream().map(this::convertToDto).collect(Collectors.toList());
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

    //Convert Entity to DTO
    private VetDto convertToDto(Vet vet)
    {
        return modelMapper.map(vet, VetDto.class);
    }

    //Convert DTO to entity
    private Vet convertToEntity(VetDto vetDto)
    {
        Vet vet = modelMapper.map(vetDto, Vet.class);
        if(vetDto.getId() != null)
        {
            Vet oldVet = vetService.findById(vetDto.getId());
            vet.setIdVet(oldVet.getIdVet());
        }
        return vet;
    }
}
