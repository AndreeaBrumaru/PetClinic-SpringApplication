package com.practice.petclinicspringapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.petclinicspringapplication.dto.VetDto;
import com.practice.petclinicspringapplication.dto.VisitDto;
import com.practice.petclinicspringapplication.model.Pet;
import com.practice.petclinicspringapplication.model.Vet;
import com.practice.petclinicspringapplication.service.IVetService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VetController.class)
public class VetControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IVetService vetService;
    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void testFindById() throws Exception {
        //GIVEN
        Mockito.when(vetService.findById(ArgumentMatchers.any())).thenReturn(new VetDto(1L, "Ichiban", "Kasuga"));

        //WHEN
        String mvcResultAsString = mvc.perform(MockMvcRequestBuilders.get("/vets/" + 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        //THEN
        VetDto response = new ObjectMapper().readValue(mvcResultAsString, VetDto.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Ichiban", response.getFirstName());
        Assertions.assertEquals("Kasuga", response.getLastName());
    }

    //Tests findAll
    @Test
    public void testFindAllOk() throws Exception
    {
        VetDto o1 = new VetDto(1L, "Andreea", "B.");
        VetDto o2 = new VetDto(2L, "Saint", "G.");

        List<VetDto> allVets = Arrays.asList(o1, o2);

        //GIVEN
        Mockito.when(vetService.findAll()).thenReturn(allVets);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/vets")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    //Tests if the validation works
    @Test
    public void postRequestValidationPassed() throws Exception{
        Vet o1 = new Vet("Andreea", "B.");

        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(o1);
        Mockito.doNothing().when(vetService).add(o1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/vets")
                        .content(asJsonString(o1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void postRequestValidationFailed() throws Exception{
        Vet o1 = new Vet("", "");

        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(o1);
        Mockito.doNothing().when(vetService).add(o1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.post("/vets")
                        .content(asJsonString(o1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    //Tests update
    @Test
    public void putRequest() throws Exception{
        Vet o1 = new Vet(1L, "test", "t.");
        Vet o2 = new Vet(2L, "Andreea", "B.");

        //GIVEN
        Mockito.when(modelMapper.map(any(), any())).thenReturn(o2);
        Mockito.doNothing().when(vetService).update(1L, o2);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.put("/vets/" + 1)
                        .content(asJsonString(o2))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests delete
    @Test
    public void deleteRequest() throws Exception{
        Vet o1 = new Vet(1L, "test", "t.");

        //GIVEN
        Mockito.doNothing().when(vetService).add(o1);

        //WHEN
        mvc.perform(MockMvcRequestBuilders.delete("/vets/" + 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //Tests getVisits
    @Test
    public void testFindAllVisits() throws Exception
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
        Mockito.when(vetService.findAllVisits(1L)).thenReturn(Arrays.asList(vi1, vi2));

        //WHEN
        mvc.perform(MockMvcRequestBuilders.get("/vets/" + 1L + "/visits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
