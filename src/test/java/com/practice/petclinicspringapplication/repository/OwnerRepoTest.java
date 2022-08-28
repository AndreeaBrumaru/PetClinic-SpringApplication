package com.practice.petclinicspringapplication.repository;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.model.Visit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
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

    private Owner owner;
    private Pet pet1;
    private Pet pet2;
    private Vet vet;
    private Visit v1;
    private Visit v2;
    @BeforeEach
    public void init()
    {
        owner = new Owner("test", "test");
        pet1 = new Pet( "test", "test", owner);
        pet2 = new Pet( "test", "test", owner);
        vet = new Vet("test", "test");
        v1 = new Visit("test", LocalDate.now(), pet1, vet);
        v2 = new Visit("test", LocalDate.now(), pet2, vet);
        ownerRepo.save(owner);
        petRepo.save(pet1);
        petRepo.save(pet2);
        vetRepo.save(vet);
        visitRepo.save(v1);
        visitRepo.save(v2);
    }

    @Test
    public void selectOwnerPets() throws Exception
    {
        List<Pet> pets = ownerRepo.ownerPets(owner);
        Assertions.assertNotNull(pets);
        Assertions.assertEquals(Arrays.asList(pet1, pet2), pets);
    }

    @Test
    public void selectOwnerVisits() throws Exception
    {
        List<Visit> visits = ownerRepo.ownerVisits(owner);
        Assertions.assertNotNull(visits);
        Assertions.assertEquals(Arrays.asList(v1, v2), visits);
    }
}
