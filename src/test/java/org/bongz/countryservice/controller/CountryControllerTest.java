package org.bongz.countryservice.controller;

import org.bongz.countryservice.dto.CountryDTO;
import org.bongz.countryservice.service.CountryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @Test
    void shouldGetAllCountries() throws Exception {
        List<CountryDTO> mockCountries = Arrays.asList(
                new CountryDTO("USA", "🇺🇸"),
                new CountryDTO("India", "🇮🇳"),
                new CountryDTO("South Africa", "🇿🇦")
        );

        when(countryService.getAllCountries()).thenReturn(mockCountries);

        mockMvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("USA"))
                .andExpect(jsonPath("$[2].name").value("South Africa"));
    }
}
