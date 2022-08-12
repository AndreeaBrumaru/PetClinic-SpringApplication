package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.VetNotFoundException;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.VetRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VetService implements IVetService{
    private final VetRepo vetRepo;

    //Constructor
    public VetService(VetRepo vetRepo) {
        this.vetRepo = vetRepo;
    }

    //Methods
    //Add new vet
    @Override
    public void add(Vet newVet)
    {
        vetRepo.save(newVet);
        System.out.println("Added new vet: " + newVet);
    }

    //Find vet by id
    @Override
    public Vet findById(Long idVet){
        return vetRepo.findById(idVet).orElseThrow(()-> new VetNotFoundException(idVet));
    }

    //Find all vets
    @Override
    public List<Vet> findAll()
    {
        var it = vetRepo.findAll();
        List<Vet> vets = new ArrayList<>();
        it.forEach(vets::add);
        if(vets.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return vets;
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
        Vet oldVet = findById(id);
        oldVet.setFirstName(vet.getFirstName());
        oldVet.setLastName(vet.getLastName());
        vetRepo.save(oldVet);
    }
    //delete vet by id
    @Override
    public void deleteById(Long idVet)
    {
        System.out.println("Deleting: " + vetRepo.findById(idVet));
        vetRepo.deleteById(idVet);
    }
}

