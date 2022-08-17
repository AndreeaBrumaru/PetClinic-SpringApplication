package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService implements IPetService{
    private final PetRepo petRepo;
    private final OwnerRepo ownerRepo;
    private final ModelMapper modelMapper;

    //Constructor
    public PetService(PetRepo petRepo, OwnerRepo ownerRepo, ModelMapper modelMapper) {
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add new pet
    @Override
    public void add(Pet newPet)
    {
        petRepo.save(newPet);
    }

    //Find pet by id
    @Override
    public PetDto findById(Long idPet) throws RuntimeException
    {
        Pet pet = findPetService(idPet);
        return convertToDto(pet);
    }

    //Find all pets
    @Override
    public List<PetDto> findAll()
    {
        List<Pet> pets = petRepo.findAll();
        if(pets.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return pets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Find by owner
    @Override
    public List<PetDto> findByOwner(Long ownerId)
    {
        List<Pet> pets = petRepo.findByOwnerId(ownerId);
        return pets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all pets
    @Override
    public Long count()
    {
        return petRepo.count();
    }

    //update pet by id
    @Override
    public void update(Long id, Pet pet, Long ownerId)
    {
        Pet oldPet = findPetService(id);
        oldPet.setNamePet(pet.getNamePet());
        oldPet.setPetType(pet.getPetType());
        oldPet.setBirthDate(pet.getBirthDate());
        oldPet.setOwner(ownerRepo.findById(ownerId).orElseThrow(() -> new OwnerNotFoundException(ownerId)));
        petRepo.save(oldPet);
    }
    //delete vet by id
    @Override
    public void deleteById(Long idPet)
    {
        petRepo.deleteById(idPet);
    }

    //Convert Entity to dto
    private PetDto convertToDto(Pet pet)
    {
        return modelMapper.map(pet, PetDto.class);
    }

    //Find pet, used only by PetService
    private Pet findPetService(Long idPet)
    {
        return petRepo.findById(idPet).orElseThrow(() -> new PetNotFoundException(idPet));
    }
}
