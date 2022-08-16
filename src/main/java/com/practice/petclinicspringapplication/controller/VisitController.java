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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void add(@RequestParam Optional<Long> id, @RequestParam Long petId, @RequestParam Long vetId, @RequestBody Visit visit)
    {
        if(id.isPresent())
        {
            throw new UpdateObjectInPostException();
        }
        visit.setDateOfVisit(LocalDate.now());
        visit.setPet(petRepo.findById(petId).orElseThrow(() -> new PetNotFoundException(petId)));
        visit.setVet(vetRepo.findById(vetId).orElseThrow(() -> new VetNotFoundException(vetId)));
        visitService.add(visit);
    }

    //Find visit by id
    @GetMapping("/visits/{id}")
    public VisitDto findVisit(@PathVariable Long id)
    {
        return convertToDto(visitService.findById(id));
    }

    //See all visits
    @GetMapping("/visits")
    public Iterable<VisitDto> getVisits() {
        List<Visit> visits = visitService.findAll();
        return visits.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    //Count all visits
    @GetMapping("/visits/count")
    public Long count()
    {
        return visitService.count();
    }

    //Update a visit
    @PutMapping("/visits/{id}")
    public void update(@PathVariable Long id, @RequestParam Long petId, @RequestParam Long vetId, @RequestBody Visit visit)
    {
        visitService.update(id, visit, petId, vetId);
    }

    //delete visit by id
    @DeleteMapping("/visits/{id}")
    public void delete(@PathVariable Long id)
    {
        visitService.deleteById(id);
    }

    //Convert to DTO
    private VisitDto convertToDto(Visit visit)
    {
        return modelMapper.map(visit, VisitDto.class);
    }

    //Convert to Entity
    private Visit convertToEntity(VisitDto visitDto)
    {
        Visit visit = modelMapper.map(visitDto, Visit.class);
        if(visitDto.getId() != null)
        {
            Visit oldVisit = visitService.findById(visitDto.getId());
            visit.setIdVisit(oldVisit.getIdVisit());
        }
        return visit;
    }
}
