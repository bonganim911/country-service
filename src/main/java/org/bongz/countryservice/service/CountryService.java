package org.bongz.countryservice.service;

import org.bongz.countryservice.dto.CountryDTO;
import org.bongz.countryservice.model.Country;
import org.bongz.countryservice.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Convert a Country entity to a CountryDTO
    private CountryDTO convertToCountryDTO(Country country) {
        return new CountryDTO(
                country.getName(),
                country.getFlag()
        );
    }
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(this::convertToCountryDTO)
                .collect(Collectors.toList());
    }
}
