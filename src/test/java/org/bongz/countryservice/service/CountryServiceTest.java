package org.bongz.countryservice.service;

import org.bongz.countryservice.dto.CountryDTO;
import org.bongz.countryservice.model.Country;
import org.bongz.countryservice.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void serviceShouldReturnAllCountries() {
        List<Country> mockedCountries = Arrays.asList(
                new Country("Nigeria", "🇳🇬"),
                new Country("Egypt", "🇪🇬")
        );

        when(countryRepository.findAll()).thenReturn(mockedCountries);

        List<CountryDTO> countries = countryService.getAllCountries();

        assertEquals(2, countries.size());
        assertEquals("Nigeria", countries.get(0).name());
        assertEquals("Egypt", countries.get(1).name());
    }
}
