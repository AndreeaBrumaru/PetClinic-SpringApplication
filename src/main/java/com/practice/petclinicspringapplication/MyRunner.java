package com.practice.petclinicspringapplication;

import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.VetRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final VetRepo vetRepo;

    //Constructor
    public MyRunner(VetRepo vetRepo) {
        this.vetRepo = vetRepo;
    }

    //Methods
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        logger.info("initializing test vets");

        Vet v1 = new Vet("Andreea", "B.");
        vetRepo.save(v1);

        Vet v2 = new Vet("Flavius", "A.");
        vetRepo.save(v2);

        Vet v3 = new Vet("Ioana", "C.");
        vetRepo.save(v3);
    }
}
