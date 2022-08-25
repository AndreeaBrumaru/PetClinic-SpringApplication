package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.model.Visit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class OwnerRepoTest {

    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    PetRepo petRepo;
    @Autowired
    VisitRepo visitRepo;
    @Autowired
    VetRepo vetRepo;

    @Test
    public void selectOwnerPets() throws Exception
    {
        Owner owner = new Owner("test", "test");
        Pet pet1 = new Pet( "test", "test", owner);
        Pet pet2 = new Pet( "test", "test", owner);
        ownerRepo.save(owner);
        petRepo.save(pet1);
        petRepo.save(pet2);
        List<Pet> pets = ownerRepo.ownerPets(owner);
        Assertions.assertNotNull(pets);
    }

    @Test
    public void selectOwnerVisits() throws Exception
    {
        Owner owner = new Owner("test", "test");
        Pet pet1 = new Pet( "test", "test", owner);
        Pet pet2 = new Pet( "test", "test", owner);
        Vet vet = new Vet("test", "test");
        Visit v1 = new Visit("test", LocalDate.now(), pet1, vet);
        Visit v2 = new Visit("test", LocalDate.now(), pet2, vet);
        ownerRepo.save(owner);
        petRepo.save(pet1);
        petRepo.save(pet2);
        vetRepo.save(vet);
        visitRepo.save(v1);
        visitRepo.save(v2);
        List<Visit> visits = ownerRepo.ownerVisits(owner);
        Assertions.assertNotNull(visits);
    }
}
