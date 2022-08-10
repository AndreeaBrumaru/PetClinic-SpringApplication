package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.VisitRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitService
{
    private final VisitRepo visitRepo;


    //Constructor
    public VisitService(VisitRepo visitRepo) {
        this.visitRepo = visitRepo;
    }

    //Methods
    //Add new visit
    public void add(Visit visit)
    {
        visitRepo.save(visit);
        System.out.println("Added new visit receipt: " + visit);
    }

    //Find visit by id
    public Optional<Visit> findById(Long idVisit)
    {
        return visitRepo.findById(idVisit);
    }

    //Find all visits
    public List<Visit> findAll()
    {
        var it = visitRepo.findAll();
        List<Visit> visits = new ArrayList<>();
        it.forEach(visits::add);
        return visits;
    }

    //Count all visits
    public Long count()
    {
        return visitRepo.count();
    }

    //update visit by id
    //TODO Implement visit update method

    //delete visit by id
    public void deleteById(Long idVisit)
    {
        System.out.println("Deleting: " + visitRepo.findById(idVisit));
        visitRepo.deleteById(idVisit);
    }

}
