package dev.cfan.spring6restmvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class BeerControllerTest {

    MockMvc mockMvc;

    @Captor
    BeerService beerService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    @Captor
    ArgumentCaptor<String> typeCaptor;

    @Test
    void getBeer() throws Exception {
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString("Here is your beer.")))
                .andExpect(status().isOk());

        ArgumentCaptor<BeerService> beerServiceArgumentCaptor = ArgumentCaptor.forClass(BeerService.class);
        beerServiceArgumentCaptor.capture();


    }

        @Test
        void getAnotherBeer() throws Exception {
            String beerType = "lager";

            mockMvc.perform(post("/api/v1/beer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(beerType)))
                    .andExpect(status().isOk());

            verify(beerService).getBeer(typeCaptor.capture());
            String capturedType = typeCaptor.getValue();

            assertThat(capturedType).isEqualTo(beerType);
        }
    }

