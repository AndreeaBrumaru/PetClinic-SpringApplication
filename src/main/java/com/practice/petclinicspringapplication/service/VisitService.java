package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.exception.VetNotFoundException;
import com.practice.petclinicspringapplication.exception.VisitNotFoundException;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.repository.VisitRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService implements IVisitService
{
    private final VisitRepo visitRepo;
    private final PetRepo petRepo;
    private final VetRepo vetRepo;
    private final ModelMapper modelMapper;

    //Constructor
    public VisitService(VisitRepo visitRepo, PetRepo petRepo, VetRepo vetRepo, ModelMapper modelMapper) {
        this.visitRepo = visitRepo;
        this.petRepo = petRepo;
        this.vetRepo = vetRepo;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add new visit
    @Override
    public void add(Visit visit)
    {
        visitRepo.save(visit);
    }

    //Find visit by id
    @Override
    public VisitDto findById(Long idVisit)
    {
        Visit visit = findVisitService(idVisit);
        return convertToDto(visit);
    }

    //Find all visits
    @Override
    public List<VisitDto> findAll()
    {
        List<Visit> visits = visitRepo.findAll();
        if(visits.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return visits.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Find visit by vet
    @Override
    public List<VisitDto> findByVet(Long vetId)
    {
        List<Visit> visits = visitRepo.findByVetId(vetId);
        return visits.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Find visit by pet
    @Override
    public List<VisitDto> findByPet(Long petId)
    {
        List<Visit> visits = visitRepo.findByPetId(petId);
        return visits.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all visits
    @Override
    public Long count()
    {
        return visitRepo.count();
    }

    //update visit by id
    @Override
    public void update(Long id, Visit visit, Long petId, Long vetId)
    {
        Visit oldVisit = findVisitService(id);
        oldVisit.setReasonForVisit(visit.getReasonForVisit());
        oldVisit.setDateOfVisit(visit.getDateOfVisit());
        oldVisit.setPet(petRepo.findById(petId).orElseThrow(() -> new PetNotFoundException(petId)));
        oldVisit.setVet(vetRepo.findById(vetId).orElseThrow(() -> new VetNotFoundException(vetId)));
        visitRepo.save(oldVisit);
    }

    //delete visit by id
    @Override
    public void deleteById(Long idVisit)
    {
        visitRepo.deleteById(idVisit);
    }

    //Convert to DTO
    private VisitDto convertToDto(Visit visit)
    {
        return modelMapper.map(visit, VisitDto.class);
    }

    //Find visit, used only by VisitService
    private Visit findVisitService(Long idVisit)
    {
        return visitRepo.findById(idVisit).orElseThrow(() -> new VisitNotFoundException(idVisit));
    }
}
