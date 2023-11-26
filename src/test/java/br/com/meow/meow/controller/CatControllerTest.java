package br.com.meow.meow.controller;

import br.com.meow.meow.model.Cat;
import br.com.meow.meow.service.CatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@WebMvcTest(CatController.class)
public class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CatService catService;

    @Test
    public void testGetCatByIdNotFound() throws Exception {
        Cat cat = new Cat();
        cat.setId(1);
        cat.setName("Fluffy");

        List<Cat> catList = Arrays.asList(cat);

        // Mock the behavior of the service
        when(catService.findAll()).thenReturn(catList);

        mockMvc.perform(get("/cats/PSIPSIPSIPSI/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCats() throws Exception {
        // Create a list of sample Cat objects
        Cat cat1 = new Cat();
        cat1.setId(1);
        cat1.setName("Whiskers");

        Cat cat2 = new Cat();
        cat2.setId(2);
        cat2.setName("Fluffy");

        List<Cat> catList = Arrays.asList(cat1, cat2);

        // Mock the behavior of the service
        when(catService.findAll()).thenReturn(catList);

        // Perform GET request to the /cats/gatinhogatinhogatinho endpoint
        mockMvc.perform(get("/cats/gatinhogatinhogatinho"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Whiskers"))
                .andExpect(jsonPath("$[1].name").value("Fluffy"));

        // Verify that the service method was called
        verify(catService, times(1)).findAll();
    }

}
