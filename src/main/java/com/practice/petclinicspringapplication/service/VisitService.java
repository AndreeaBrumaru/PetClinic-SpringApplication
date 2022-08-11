package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.exception.*;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.repository.VisitRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VisitService implements IVisitService
{
    private final VisitRepo visitRepo;
    private final OwnerRepo ownerRepo;
    private final PetRepo petRepo;
    private final VetRepo vetRepo;


    //Constructor
    public VisitService(VisitRepo visitRepo, OwnerRepo ownerRepo, PetRepo petRepo, VetRepo vetRepo) {
        this.visitRepo = visitRepo;
        this.ownerRepo = ownerRepo;
        this.petRepo = petRepo;
        this.vetRepo = vetRepo;
    }

    //Methods
    //Add new visit
    @Override
    public void add(Visit visit)
    {
        visitRepo.save(visit);
        System.out.println("Added new visit receipt: " + visit);
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
        var it = visitRepo.findAll();
        List<Visit> visits = new ArrayList<>();
        it.forEach(visits::add);
        if(visits.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return visits;
    }

    //Count all visits
    @Override
    public Long count()
    {
        return visitRepo.count();
    }

    //update visit by id
    @Override
    public void update(Long id, String reason, LocalDate date, Long owner_id, Long pet_id, Long vet_id)
    {
        Visit visit = findById(id);
        visit.setReasonForVisit(reason);
        visit.setDateOfVisit(date);
        visit.setOwner(ownerRepo.findById(owner_id).orElseThrow(() -> new OwnerNotFoundException(owner_id)));
        visit.setPet(petRepo.findById(pet_id).orElseThrow(() -> new PetNotFoundException(pet_id)));
        visit.setVet(vetRepo.findById(vet_id).orElseThrow(() ->  new VetNotFoundException(vet_id)));
        visitRepo.save(visit);
    }

    //delete visit by id
    @Override
    public void deleteById(Long idVisit)
    {
        System.out.println("Deleting: " + visitRepo.findById(idVisit));
        visitRepo.deleteById(idVisit);
    }

}
