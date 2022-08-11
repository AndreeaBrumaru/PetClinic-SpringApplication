package com.practice.petclinicspringapplication;

import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.repository.VisitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final VetRepo vetRepo;
    private final PetRepo petRepo;
    private final OwnerRepo ownerRepo;
    private final VisitRepo visitRepo;

    //Constructor
    public MyRunner(VetRepo vetRepo, PetRepo petRepo, OwnerRepo ownerRepo, VisitRepo visitRepo) {
        this.vetRepo = vetRepo;
        this.petRepo = petRepo;
        this.ownerRepo = ownerRepo;
        this.visitRepo = visitRepo;
    }

    //Methods
    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        logger.info(">> initializing test vets");
//
//        Vet v1 = new Vet("Miruna", "N.");
//        vetRepo.save(v1);
//
//        logger.info(">> initializing test pets");
//
//        Pet p1 = new Pet("Perry", "Platypus");
//        petRepo.save(p1);
//
//        logger.info(">> initializing test owners");
//
//        Owner o1 = new Owner("Leo", "C.");
//        ownerRepo.save(o1);
//
//        logger.info(">> initializing visit owners");
//
//        Visit vis1 = new Visit("Regular check-up.", LocalDate.now(), o1, p1, v1);
//        visitRepo.save(vis1);
    }
}
