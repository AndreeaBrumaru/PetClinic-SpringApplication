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
import java.util.Arrays;

@SpringBootTest
public class VisitRepoTest {
    @Autowired
    OwnerRepo ownerRepo;
    @Autowired
    PetRepo petRepo;
    @Autowired
    VisitRepo visitRepo;
    @Autowired
    VetRepo vetRepo;

    @Test
    public void findByVetId() throws Exception
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
        Assertions.assertEquals(Arrays.asList(v1, v2), visitRepo.findByVetId(vet.getIdVet()));
    }

    @Test
    public void findByPetId() throws Exception
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
        Assertions.assertEquals(Arrays.asList(v1), visitRepo.findByPetId(pet1.getIdPet()));
    }
}
