package org.bongz.countryservice.controller;

import org.bongz.countryservice.dto.CountryDTO;
import org.bongz.countryservice.model.Country;
import org.bongz.countryservice.service.CountryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<CountryDTO> getAllCountries(){
        return countryService.getAllCountries();
    }

}
