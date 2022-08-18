package com.practice.petclinicspringapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.model.Visit;
import com.practice.petclinicspringapplication.repository.PetRepo;
import com.practice.petclinicspringapplication.repository.VetRepo;
import com.practice.petclinicspringapplication.service.IVisitService;
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
import java.util.Optional;

import static com.practice.petclinicspringapplication.controller.OwnerControllerTest.asJsonString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VisitController.class)
public class VisitControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IVisitService visitService;
    @MockBean
    private PetRepo petRepo;
    @MockBean
    private VetRepo vetRepo;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testFindById() throws Exception {
        Pet pet = new Pet("Sisi", "Corgi");
        Vet vet = new Vet("Ichiban", "Kasuga");
        VisitDto vi1 = new VisitDto(1L, "Broken everything", LocalDate.now(), pet, vet);

        //GIVEN
        Mockito.when(visitService.findById(any())).thenReturn(vi1);

        //WHEN
        String mvcResultAsString = mvc.perform(MockMvcRequestBuilders.get("/visits/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        //THEN
        VisitDto response = new ObjectMapper().registerModule(new JavaTimeModule()).readValue(mvcResultAsString, VisitDto.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Broken everything", response.getReasonForVisit());
        Assertions.assertEquals(LocalDate.now(), response.getDateOfVisit());
        Assertions.assertEquals(pet, response.getPet());
        Assertions.assertEquals(vet, response.getVet());
    }

    //Tests findAll
    @Test
    public void testFindAllOk() throws Exception
    {
        Pet p1 = new Pet("a", "a");
        Pet p2 = new Pet("a", "a");
        Vet v1 = new Vet(1L, "te", "st");
        Vet v2 = new Vet(2L, "te", "st");
        VisitDto vi1 = new VisitDto(3L, "a", LocalDate.now(), p1, v1);
        VisitDto vi2 = new VisitDto(4L, "a", LocalDate.now(), p1, v1);
        VisitDto vi3 = new VisitDto(5L, "a", LocalDate.now(), p1, v2);
        VisitDto vi4 = new VisitDto(6L, "a", LocalDate.now(), p2, v2);
        VisitDto vi5 = new VisitDto(7L, "a", LocalDate.now(), p2, v2);

        //GIVEN
        Mockito.when(visitService.findAll()).thenReturn(Arrays.asList(vi1, vi2, vi3, vi4, vi5));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/visits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    //Tests if the validation works
    @Test
    public void postRequestValidationPassed() throws Exception{
        Pet p1 = new Pet(1L, "a", "a");
        Vet v1 = new Vet(2L, "te", "st");
        Visit vi1 = new Visit(3L, "a");

        //GIVEN
        Mockito.doNothing().when(visitService).add(vi1);
        Mockito.when(modelMapper.map(any(), any())).thenReturn(vi1);
        Mockito.when(petRepo.findById(any())).thenReturn(Optional.of(p1));
        Mockito.when(vetRepo.findById(any())).thenReturn(Optional.of(v1));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/visits?petId=" + 1L + "&vetId=" + 2L)
                        .content(asJsonString(vi1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postRequestValidationFailed() throws Exception{
        Pet p1 = new Pet(1L, "a", "a");
        Vet v1 = new Vet(2L, "te", "st");
        Visit vi1 = new Visit(3L, "");

        //GIVEN
        Mockito.doNothing().when(visitService).add(vi1);
        Mockito.when(modelMapper.map(any(), any())).thenReturn(vi1);
        Mockito.when(petRepo.findById(any())).thenReturn(Optional.of(p1));
        Mockito.when(vetRepo.findById(any())).thenReturn(Optional.of(v1));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/visits?petId=" + 1L + "&vetId=" + 2L)
                        .content(asJsonString(vi1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //Tests update
    @Test
    public void putRequest() throws Exception{
        Pet p1 = new Pet("a", "a");
        Vet v1 = new Vet(1L, "te", "st");
        Visit vi1 = new Visit(3L, "s", p1, v1);
        Visit vi2 = new Visit(4L, "a", p1, v1);

        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(vi2);
        Mockito.doNothing().when(visitService).update(3L, vi2);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/visits/" + 3)
                        .content(asJsonString(vi2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests delete
    @Test
    public void deleteRequest() throws Exception{
        Pet p1 = new Pet("a", "a");
        Vet v1 = new Vet(1L, "te", "st");
        Visit vi1 = new Visit(3L, "s", LocalDate.now(), p1, v1);

        //GIVEN
        Mockito.doNothing().when(visitService).deleteById(vi1.getIdVisit());

        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/visits/" + 3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests getByVet
    @Test
    public void testFindBYVets() throws Exception
    {
        Pet p1 = new Pet("a", "a");
        Pet p2 = new Pet("a", "a");
        Vet v1 = new Vet(1L, "te", "st");
        Vet v2 = new Vet(2L, "te", "st");
        VisitDto vi1 = new VisitDto(3L, "a", LocalDate.now(), p1, v1);
        VisitDto vi2 = new VisitDto(4L, "a", LocalDate.now(), p1, v1);
        VisitDto vi3 = new VisitDto(5L, "a", LocalDate.now(), p1, v2);
        VisitDto vi4 = new VisitDto(6L, "a", LocalDate.now(), p2, v2);
        VisitDto vi5 = new VisitDto(7L, "a", LocalDate.now(), p2, v2);

        //GIVEN
        Mockito.when(visitService.findByVet(1L)).thenReturn(Arrays.asList(vi1, vi2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/visits/vet/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    //TODO Test getByPet
    @Test
    public void testFindBYPets() throws Exception
    {
        Pet p1 = new Pet(8L,"a", "a");
        Pet p2 = new Pet(9L, "a", "a");
        Vet v1 = new Vet(1L, "te", "st");
        Vet v2 = new Vet(2L, "te", "st");
        VisitDto vi1 = new VisitDto(3L, "a", LocalDate.now(), p1, v1);
        VisitDto vi2 = new VisitDto(4L, "a", LocalDate.now(), p1, v1);
        VisitDto vi3 = new VisitDto(5L, "a", LocalDate.now(), p1, v2);
        VisitDto vi4 = new VisitDto(6L, "a", LocalDate.now(), p2, v2);
        VisitDto vi5 = new VisitDto(7L, "a", LocalDate.now(), p2, v2);

        //GIVEN
        Mockito.when(visitService.findByPet(8L)).thenReturn(Arrays.asList(vi1, vi2, vi3));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/visits/pet/" + 8)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}
