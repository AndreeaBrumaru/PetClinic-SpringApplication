package com.practice.petclinicspringapplication.service;

import com.practice.petclinicspringapplication.exception.NoDataFoundException;
import com.practice.petclinicspringapplication.exception.OwnerNotFoundException;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService implements IOwnerService{
    private final OwnerRepo ownerRepo;

    //Constructor
    public OwnerService(OwnerRepo ownerRepo) {
        this.ownerRepo = ownerRepo;
    }

    //Methods
    //Add new owner
    @Override
    public void add(Owner newOwner)
    {
        ownerRepo.save(newOwner);
    }

    //Find owner by id
    @Override
    public Owner findById(Long idOwner) {
        return ownerRepo.findById(idOwner).orElseThrow(() -> new OwnerNotFoundException(idOwner));
    }

    //Find all owners
    @Override
    public List<Owner> findAll()
    {
        List<Owner> list = ownerRepo.findAll();
        if(list.isEmpty())
        {
            throw new NoDataFoundException();
        }
        return list;
    }

    //Count all owner
    @Override
    public Long count()
    {
        return ownerRepo.count();
    }

    //update owner by id
    @Override
    public void update(Long id, Owner owner)
    {
        Owner oldOwner = findById(id);
        oldOwner.setFirstName(owner.getFirstName());
        oldOwner.setLastName(owner.getLastName());
        ownerRepo.save(oldOwner);
    }

    //delete owner by id
    @Override
    public void deleteById(Long idOwner)
    {
        ownerRepo.deleteById(idOwner);
    }

}
