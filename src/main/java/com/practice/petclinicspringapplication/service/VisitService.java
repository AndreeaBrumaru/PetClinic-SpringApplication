package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.exception.VetNotFoundException;
import com.practice.petclinicspringapplication.exception.VisitNotFoundException;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.repository.VisitRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService implements IVisitService
{
    private final VisitRepo visitRepo;
    private final PetRepo petRepo;
    private final VetRepo vetRepo;


    //Constructor
    public VisitService(VisitRepo visitRepo, PetRepo petRepo, VetRepo vetRepo) {
        this.visitRepo = visitRepo;
        this.petRepo = petRepo;
        this.vetRepo = vetRepo;
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
    public Visit findById(Long idVisit)
    {
        return visitRepo.findById(idVisit).orElseThrow(() -> new VisitNotFoundException(idVisit));
    }

    //Find all visits
    @Override
    public List<Visit> findAll()
    {
        List<Visit> visits = visitRepo.findAll();
        if(visits.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return visits;
    }

    //Find visit by vet
    @Override
    public List<Visit> findByVet(Long vetId)
    {
        return visitRepo.findByVetId(vetId);
    }

    //Find visit by pet
    @Override
    public List<Visit> findByPet(Long petId)
    {
        return visitRepo.findByPetId(petId);
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
        Visit oldVisit = findById(id);
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

}
