package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.OwnerDto;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.service.IOwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//TODO Importing lists is wierd
@RestController
public class OwnerController {
    private final IOwnerService ownerService;
    private final ModelMapper modelMapper;

    //Constructor
    public OwnerController(IOwnerService ownerService, ModelMapper modelMapper) {
        this.ownerService = ownerService;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add owner
    //TODO Is this supposed to be DTO'd?
    @PostMapping("/owners")
    public void add(@RequestParam Optional<Long> id, @RequestBody Owner owner)
    {
        if(id.isPresent())
        {
            //Throws exception if id has been inserted
            throw new UpdateObjectInPostException();
        }
        ownerService.add(owner);
    }

    //Find owner by id
    //TODO Do this for visit
    @GetMapping("/owners/{id}")
    public OwnerDto findOwner(@PathVariable Long id)
    {
        return convertToDto(ownerService.findById(id));
    }

    //See all owners
    //TODO Do this to visit
    @GetMapping("/owners")
    public Iterable<OwnerDto> getOwners() {
        List<Owner> owners = ownerService.findAll();
        return owners.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all owners
    @GetMapping("/owners/count")
    public Long count()
    {
        return ownerService.count();
    }

    //Update an owner
    //TODO Is this supposed to be DTO'd?
    @PutMapping("/owners/{id}")
    public void update(@PathVariable Long id, @RequestBody Owner owner)
    {
        ownerService.update(id, owner);
    }

    //delete owner by id
    //TODO Is this supposed to be DTO'd?
    @DeleteMapping("/owners/{id}")
    public void delete(@PathVariable Long id)
    {
        ownerService.deleteById(id);
    }

    //TODO Do this to visit
    //Convert entity to Dto
    private OwnerDto convertToDto(Owner owner)
    {
        return modelMapper.map(owner, OwnerDto.class);
    }

    //convert Dto to entity
    private Owner convertToEntity(OwnerDto ownerDto) throws ParseException
    {
        Owner owner = modelMapper.map(ownerDto, Owner.class);
        if(ownerDto.getId() != null)
        {
            Owner oldOwner = ownerService.findById(ownerDto.getId());
            owner.setIdOwner(oldOwner.getIdOwner());
        }
        return owner;
    }
}
