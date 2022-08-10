package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.VetRepo;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VetService {
    private final VetRepo vetRepo;

    //Constructor
    public VetService(VetRepo vetRepo) {
        this.vetRepo = vetRepo;
    }

    //Methods
    //Add new vet
    public void add(Vet newVet)
    {
        vetRepo.save(newVet);
        System.out.println("Added new vet: " + newVet);
    }

    //Find vet by id
    public Optional<Vet> findById(Long idVet)
    {
        return vetRepo.findById(idVet);
    }

    //Find all vets
    public List<Vet> findAll()
    {
        var it = vetRepo.findAll();
        List<Vet> vets = new ArrayList<>();
        it.forEach(vets::add);
        return vets;
    }

    //Count all vets
    public Long count()
    {
        return vetRepo.count();
    }

    //update vet by id
    //TODO Implement vet update method
    public void update(Long id, String firstName, String lastName)
    {
        Optional<Vet> vet = vetRepo.findById(id);
    }
    //delete vet by id
    public void deleteById(Long idVet)
    {
        System.out.println("Deleting: " + vetRepo.findById(idVet));
        vetRepo.deleteById(idVet);
    }
}

