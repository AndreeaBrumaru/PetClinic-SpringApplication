package com.practice.petclinicspringapplication;

import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final VetRepo vetRepo;
    private final PetRepo petRepo;
    private final OwnerRepo ownerRepo;

    //Constructor
    public MyRunner(VetRepo vetRepo, PetRepo petRepo, OwnerRepo ownerRepo) {
        this.vetRepo = vetRepo;
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
    }

    //Methods
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info(">> initializing test vets");

        Vet v1 = new Vet("Diana", "A.");
        vetRepo.save(v1);

        Vet v2 = new Vet("Leo", "F.");
        vetRepo.save(v2);

        Vet v3 = new Vet("Ioana", "C.");
        vetRepo.save(v3);

        logger.info(">> initializing test pets");

        Pet p1 = new Pet("Polly", "Dog");
        petRepo.save(p1);

        Pet p2 = new Pet("Chocola", LocalDate.of(2018, 03, 06), "Cat");
        petRepo.save(p2);

        logger.info(">> initializing test owners");

        Owner o1 = new Owner("Andreea", "B.");
        ownerRepo.save(o1);
    }
}
