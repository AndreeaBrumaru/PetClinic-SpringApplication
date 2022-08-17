package com.practice.petclinicspringapplication.controller;

import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.exception.PetNotFoundException;
import com.practice.petclinicspringapplication.exception.UpdateObjectInPostException;
import com.practice.petclinicspringapplication.exception.VetNotFoundException;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.service.IVisitService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Transient;
import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

@RestController
public class VisitController {

    private final IVisitService visitService;
    private final PetRepo petRepo;
    private final VetRepo vetRepo;
    private final ModelMapper modelMapper;

    //Constructor
    public VisitController(IVisitService visitService, PetRepo petRepo, VetRepo vetRepo, ModelMapper modelMapper) {
        this.visitService = visitService;
        this.petRepo = petRepo;
        this.vetRepo = vetRepo;
        this.modelMapper = modelMapper;
    }

    //Methods
    //Add visit
    @PostMapping("/visits")
    public ResponseEntity<String> add(@RequestParam Optional<Long> id, @RequestParam Long petId, @RequestParam Long vetId, @Valid @RequestBody VisitDto visitDto) throws ParseException {
        if(id.isPresent())
        {
            throw new UpdateObjectInPostException();
        }
        Visit visit = convertToEntity(visitDto);
        visit.setDateOfVisit(LocalDate.now());
        visit.setPet(petRepo.findById(petId).orElseThrow(() -> new PetNotFoundException(petId)));
        visit.setVet(vetRepo.findById(vetId).orElseThrow(() -> new VetNotFoundException(vetId)));
        visitService.add(visit);
        return ResponseEntity.ok("New visit added");
    }

    //Find visit by id
    @GetMapping("/visits/{id}")
    public VisitDto findVisit(@PathVariable Long id)
    {
        return visitService.findById(id);
    }

    //See all visits
    @GetMapping("/visits")
    public Iterable<VisitDto> getVisits() {
        return visitService.findAll();
    }

    //Find visit by vet
    @GetMapping("/visits/vet/{vetId}")
    public Iterable<VisitDto> getByVet(@PathVariable Long vetId)
    {
        return visitService.findByVet(vetId);
    }

    //Find visit by pet
    @GetMapping("/visits/pet/{petId}")
    public Iterable<VisitDto> getByPet(@PathVariable Long petId)
    {
        return visitService.findByPet(petId);
    }

    //Count all visits
    @GetMapping("/visits/count")
    public String count()
    {
        return "There are " + visitService.count() + " visits registered in the database";
    }

    //Update a visit
    @PutMapping("/visits/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestParam Long petId, @RequestParam Long vetId, @Valid @RequestBody VisitDto visitDto) throws ParseException {
        Visit visit = convertToEntity(visitDto);
        if(petId != null && vetId != null)
        {
            visitService.update(id, visit, petId, vetId);
            return ResponseEntity.ok("Visit updated");
        }
        visitService.update(id, visit);
        return ResponseEntity.ok("Visit updated");
    }

    //delete visit by id
    @DeleteMapping("/visits/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        visitService.deleteById(id);
        return ResponseEntity.ok("Visit deleted");
    }

    //Convert to Entity
    @Transient
    private Visit convertToEntity(VisitDto visitDto) throws ParseException
    {
        return modelMapper.map(visitDto, Visit.class);
    }
}
