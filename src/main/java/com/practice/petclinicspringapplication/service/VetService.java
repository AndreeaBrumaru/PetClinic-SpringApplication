package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.VetNotFoundException;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.VetRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetService implements IVetService{
    private final VetRepo vetRepo;
    private final ModelMapper modelMapper;

    //Constructor
    public VetService(VetRepo vetRepo, ModelMapper modelMapper) {
        this.vetRepo = vetRepo;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add new vet
    @Override
    public void add(Vet newVet)
    {
        vetRepo.save(newVet);
    }

    //Find vet by id
    @Override
    public VetDto findById(Long idVet){
        Vet vet = findVetService(idVet);
        return convertToDto(vet);
    }

    //Find all vets
    @Override
    public List<VetDto> findAll()
    {
        List<Vet> vets = vetRepo.findAll();
        if(vets.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return vets.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all vets
    @Override
    public Long count()
    {
        return vetRepo.count();
    }

    //update vet by id
    @Override
    public void update(Long id, Vet vet)
    {
        Vet oldVet = findVetService(id);
        oldVet.setFirstName(vet.getFirstName());
        oldVet.setLastName(vet.getLastName());
        vetRepo.save(oldVet);
    }
    //delete vet by id
    @Override
    public void deleteById(Long idVet)
    {
        vetRepo.deleteById(idVet);
    }

    //Convert Entity to DTO
    private VetDto convertToDto(Vet vet)
    {
        return modelMapper.map(vet, VetDto.class);
    }

    //Find vet, used only by VetService
    private Vet findVetService(Long idVet)
    {
        return vetRepo.findById(idVet).orElseThrow(() -> new VetNotFoundException(idVet));
    }
}

