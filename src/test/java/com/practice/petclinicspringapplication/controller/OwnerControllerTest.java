package com.practice.petclinicspringapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.petclinicspringapplication.dto.OwnerDto;
import com.practice.petclinicspringapplication.dto.PetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Owner;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IOwnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OwnerController.class)
public class OwnerControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IOwnerService ownerService;
    @MockBean
    private ModelMapper modelMapper;

    //Tests findById
    @Test
    public void testFindById() throws Exception {
        //GIVEN
        Mockito.when(ownerService.findById(any())).thenReturn(new OwnerDto(1L, "Mircea", "Mircea"));

        //WHEN
        String mvcResultAsString = mvc.perform(MockMvcRequestBuilders.get("/owners/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn().getResponse().getContentAsString();

        //THEN
        OwnerDto response = new ObjectMapper().readValue(mvcResultAsString, OwnerDto.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Mircea", response.getFirstName());
        Assertions.assertEquals("Mircea", response.getLastName());
    }

    //Tests findAll
    @Test
    public void testFindAllOk() throws Exception
    {
        OwnerDto o1 = new OwnerDto(1L, "Andreea", "B.");
        OwnerDto o2 = new OwnerDto(2L, "Saint", "G.");

        List<OwnerDto> allOwners = Arrays.asList(o1, o2);

        //GIVEN
        Mockito.when(ownerService.findAll()).thenReturn(allOwners);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/owners")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(allOwners.size())));
    }

    //Tests if the validation works
    @Test
    public void postRequestValidationPassed() throws Exception{
        Owner o1 = new Owner("Andreea", "B.");

        //GIVEN
        Mockito.doNothing().when(ownerService).add(o1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/owners")
                        .content(asJsonString(o1))
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }

    @Test
    public void postRequestValidationFailed() throws Exception{
        Owner o1 = new Owner("", "");

        //GIVEN
        Mockito.doNothing().when(ownerService).add(o1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/owners")
                        .content(asJsonString(o1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //Tests if count works
    @Test
    public void testCountOk() throws Exception
    {
        OwnerDto o1 = new OwnerDto(1L, "Andreea", "B.");
        OwnerDto o2 = new OwnerDto(2L, "Saint", "G.");
        OwnerDto o3 = new OwnerDto(3L, "Saint", "G.");

        //List<OwnerDto> allOwners = Arrays.asList(o1, o2, o3);

        //GIVEN Pretty sure this is useless
        //Mockito.when(ownerService.count()).thenReturn(3L);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/owners/count")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests update
    @Test
    public void putRequest() throws Exception{
        Owner o1 = new Owner(1L, "test", "t.");
        Owner o2 = new Owner(2L, "Andreea", "B.");

        //GIVEN
        Mockito.doNothing().when(ownerService).add(o1);
        Mockito.doNothing().when(ownerService).update(1L, o2);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/owners/" + 1)
                        .content(asJsonString(o2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests delete
    @Test
    public void deleteRequest() throws Exception{
        Owner o1 = new Owner(1L, "test", "t.");

        //GIVEN
        Mockito.doNothing().when(ownerService).add(o1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/owners/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests getPets
    @Test
    public void testFindAllPets() throws Exception
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
        Mockito.when(ownerService.getPets(1L)).thenReturn(allPetso1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/owners/" + o1.getIdOwner() + "/pets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    //Tests getVisits
    @Test
    public void testFindAllVisits() throws Exception
    {
        Owner o1 = new Owner(1L, "Andreea", "B.");
        Owner o2 = new Owner(2L, "Saint", "G.");
        Pet p1 = new Pet("a", "a", o1);
        Pet p2 = new Pet("a", "a", o2);
        Vet v1 = new Vet("te", "st");
        VisitDto vi1 = new VisitDto(3L, "a", LocalDate.now(), p1, v1);
        VisitDto vi2 = new VisitDto(4L, "a", LocalDate.now(), p1, v1);
        VisitDto vi3 = new VisitDto(5L, "a", LocalDate.now(), p1, v1);
        VisitDto vi4 = new VisitDto(6L, "a", LocalDate.now(), p2, v1);
        VisitDto vi5 = new VisitDto(7L, "a", LocalDate.now(), p2, v1);

        List<VisitDto> allVisitso1 = Arrays.asList(vi1, vi2, vi3);
        List<VisitDto> allVisitso2 = Arrays.asList(vi4, vi5);


        //GIVEN
        Mockito.when(ownerService.getVisits(1L)).thenReturn(allVisitso1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/owners/" + o1.getIdOwner() + "/visits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    //Methods
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
