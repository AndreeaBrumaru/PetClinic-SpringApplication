package com.guru.sftpetclinic.controllers;

import com.guru.sftpetclinic.model.Vet;
import com.guru.sftpetclinic.repositories.VetRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    private final VetRepo vetRepo;

    //Constructor
    public VetController(VetRepo vetRepo) {
        this.vetRepo = vetRepo;
    }

    //Methods
    @GetMapping("/vets")
    public Iterable<Vet> getVets()
    {
        return vetRepo.findAll();
    }
}
