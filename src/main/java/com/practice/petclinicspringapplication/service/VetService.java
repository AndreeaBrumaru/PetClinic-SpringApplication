package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.VetRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    //TODO Make custom exception
    public Vet findById(Long idVet) throws RuntimeException {
        return vetRepo.findById(idVet).orElseThrow(RuntimeException::new);
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
    public void update(Long id, String firstName, String lastName)
    {
        Vet vet = findById(id);
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vetRepo.save(vet);
    }
    //delete vet by id
    public void deleteById(Long idVet)
    {
        System.out.println("Deleting: " + vetRepo.findById(idVet));
        vetRepo.deleteById(idVet);
    }
}

