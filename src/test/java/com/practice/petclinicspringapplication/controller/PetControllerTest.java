package com.practice.petclinicspringapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.repository.OwnerRepo;
import com.practice.petclinicspringapplication.service.IPetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.practice.petclinicspringapplication.controller.OwnerControllerTest.asJsonString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)
public class PetControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IPetService petService;
    @MockBean
    private OwnerRepo ownerRepo;
    @MockBean
    private ModelMapper modelMapper;

    //Tests findById
    @Test
    public void testFindById() throws Exception{
        Owner owner = new Owner("Saint", "G.");
        //GIVEN
        Mockito.when(petService.findById(ArgumentMatchers.any())).thenReturn(new PetDto(1L, "Sisi", "Corgi",LocalDate.now(), owner));

        //WHEN
        String mvcResultAsString = mvc.perform(MockMvcRequestBuilders.get("/pets/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        //THEN
        PetDto response = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(mvcResultAsString, PetDto.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Sisi", response.getNamePet());
        Assertions.assertEquals("Corgi", response.getPetType());
        Assertions.assertEquals(LocalDate.now(), response.getBirthDate());
        Assertions.assertEquals(owner, response.getOwner());
    }

    //Tests findAll
    @Test
    public void testFindAllOk() throws Exception
    {
        PetDto p1 = new PetDto(1L, "a", "a");
        PetDto p2 = new PetDto(1L, "a", "a");

        List<PetDto> allPets = Arrays.asList(p1, p2);

        //GIVEN
        Mockito.when(petService.findAll()).thenReturn(allPets);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/pets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
    //Tests if validation works
    @Test
    public void postRequestValidationPassed() throws Exception{
        Pet p1 = new Pet("Aa", "Ba");
        //GIVEN
        Mockito.doNothing().when(petService).add(p1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/pets")
                        .content(asJsonString(p1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postRequestValidationFailed() throws Exception{
        Pet p1 = new Pet("", "");
        //GIVEN
        Mockito.doNothing().when(petService).add(p1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/pets")
                        .content(asJsonString(p1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //Tests update
    @Test
    public void putRequest() throws Exception{
        Pet p1 = new Pet(1L, "a", "a");
        Pet p2 = new Pet(2L, "b", "b");

        //GIVEN
        Mockito.doNothing().when(petService).add(p1);
        Mockito.doNothing().when(petService).update(1L, p2);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/pets/" + 1)
                        .content(asJsonString(p2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests delete
    @Test
    public void deleteRequest() throws Exception{
        Pet p1 = new Pet(1L, "test", "t.");

        //GIVEN
        Mockito.doNothing().when(petService).deleteById(1L);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/pets/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests findByOwner
    @Test
    public void testFindBYOwner() throws Exception
    {
        Owner o1 = new Owner(1L, "Andreea", "B.");
        Owner o2 = new Owner(2L, "Saint", "G.");
        PetDto p1 = new PetDto(3L, "a", "a", o1);
        PetDto p2 = new PetDto(4L, "a", "a", o1);
        PetDto p3 = new PetDto(5L, "a", "a", o1);
        PetDto p4 = new PetDto(6L, "a", "a", o2);
        PetDto p5 = new PetDto(7L, "a", "a", o2);

        List<PetDto> allPetso1 = Arrays.asList(p1, p2, p3);
        List<PetDto> allPetso2 = Arrays.asList(p4, p5);

        //GIVEN
        Mockito.when(petService.findByOwner(1L)).thenReturn(allPetso1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/pets/owner/" + o1.getIdOwner())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    //Tests findPetsVisits
    @Test
    public void testFindVisits() throws Exception
    {
        Pet p1 = new Pet(1L, "a", "a");
        Pet p2 = new Pet(2L, "a", "a");
        Vet v1 = new Vet("te", "st");
        VisitDto vi1 = new VisitDto(3L, "a", LocalDate.now(), p1, v1);
        VisitDto vi2 = new VisitDto(4L, "a", LocalDate.now(), p1, v1);
        VisitDto vi3 = new VisitDto(5L, "a", LocalDate.now(), p1, v1);
        VisitDto vi4 = new VisitDto(6L, "a", LocalDate.now(), p2, v1);
        VisitDto vi5 = new VisitDto(7L, "a", LocalDate.now(), p2, v1);

        List<VisitDto> allVisitso1 = Arrays.asList(vi1, vi2, vi3);
        List<VisitDto> allVisitso2 = Arrays.asList(vi4, vi5);

        //GIVEN
        Mockito.when(petService.findPetsVisits(1L)).thenReturn(allVisitso1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/pets/" + p1.getIdPet() + "/visits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
