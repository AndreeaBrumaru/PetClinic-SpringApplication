package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerService {
    private final OwnerRepo ownerRepo;

    //Constructor
    public OwnerService(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    //Methods
    //Add new owner
    public void add(Owner newOwner)
    {
        ownerRepo.save(newOwner);
        System.out.println("Added new owner: " + newOwner);
    }

    //Find owner by id
    //TODO Make custom exception
    public Owner findById(Long idOwner) throws RuntimeException
    {
        return ownerRepo.findById(idOwner).orElseThrow(RuntimeException::new);
    }

    //Find all owners
    public List<Owner> findAll()
    {
        var it = ownerRepo.findAll();
        List<Owner> owners = new ArrayList<>();
        it.forEach(owners::add);
        return owners;
    }

    //Count all owner
    public Long count()
    {
        return ownerRepo.count();
    }

    //update owner by id
    public void update(Long id, String firstName, String lastName)
    {
        Owner owner = findById(id);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        ownerRepo.save(owner);
    }

    //delete owner by id
    public void deleteById(Long idOwner)
    {
        System.out.println("Deleting: " + ownerRepo.findById(idOwner));
        ownerRepo.deleteById(idOwner);
    }

}
