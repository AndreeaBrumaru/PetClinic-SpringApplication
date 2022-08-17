package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.OwnerDto;
import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService implements IOwnerService{
    private final OwnerRepo ownerRepo;
    private final ModelMapper modelMapper;

    //Constructor
    public OwnerService(OwnerRepo ownerRepo, ModelMapper modelMapper) {
        this.ownerRepo = ownerRepo;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add new owner
    @Override
    public void add(Owner newOwner)
    {
        ownerRepo.save(newOwner);
    }

    //Find owner by id
    @Override
    public OwnerDto findById(Long idOwner) {
        Owner owner = findOwnerService(idOwner);
        return convertToDto(owner);
    }

    //Find all owners
    @Override
    public List<OwnerDto> findAll()
    {
        List<Owner> list = ownerRepo.findAll();
        if(list.isEmpty())
        {
            throw new NoDataFoundException();
        }

        return list.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Show all the owner's pets
    @Override
    public List<PetDto> getPets(Long ownerId)
    {
        Owner owner = findOwnerService(ownerId);
        List<Pet> pets = ownerRepo.ownerPets(owner);
        return pets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Show all the owner's visits
    @Override
    public List<VisitDto> getVisits(Long ownerId)
    {
        Owner owner = findOwnerService(ownerId);
        List<Visit> visits = ownerRepo.ownerVisits(owner);
        return visits.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all owner
    @Override
    public Long count()
    {
        return ownerRepo.count();
    }

    //update owner by id
    @Override
    public void update(Long id, Owner owner)
    {
        Owner oldOwner = findOwnerService(id);
        oldOwner.setFirstName(owner.getFirstName());
        oldOwner.setLastName(owner.getLastName());
        ownerRepo.save(oldOwner);
    }

    //delete owner by id
    @Override
    public void deleteById(Long idOwner)
    {
        ownerRepo.deleteById(idOwner);
    }

    //Convert entity to Dto
    private OwnerDto convertToDto(Owner owner)
    {
        return modelMapper.map(owner, OwnerDto.class);
    }
    private PetDto convertToDto(Pet pet)
    {
        return modelMapper.map(pet, PetDto.class);
    }
    private VisitDto convertToDto(Visit visit)
    {
        return modelMapper.map(visit, VisitDto.class);
    }

    //Find owner, used only by OwnerService
    private Owner findOwnerService(Long idOwner)
    {
        return ownerRepo.findById(idOwner).orElseThrow(() -> new OwnerNotFoundException(idOwner));
    }
}
