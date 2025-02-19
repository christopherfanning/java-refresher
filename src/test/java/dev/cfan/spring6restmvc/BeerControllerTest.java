package dev.cfan.spring6restmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class BeerControllerTest {

    MockMvc mockMvc;

    @Captor
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeer() throws Exception {
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("Here is your beer.")))
                .andExpect(status().isOk());

        ArgumentCaptor<BeerService> beerServiceArgumentCaptor = ArgumentCaptor.forClass(BeerService.class);
        beerServiceArgumentCaptor.capture();


    }


}