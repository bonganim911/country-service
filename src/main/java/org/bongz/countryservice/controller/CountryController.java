package org.bongz.countryservice.controller;

import org.bongz.countryservice.dto.CountryDTO;
import org.bongz.countryservice.dto.CountryDetailsDTO;
import org.bongz.countryservice.model.Country;
import org.bongz.countryservice.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{countryName}")
    public ResponseEntity<?>  getCountry(@PathVariable String countryName){
        return countryService.getCountryDetails(countryName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
