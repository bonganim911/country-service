package org.bongz.countryservice;

import org.bongz.countryservice.model.ApiCountry;
import org.bongz.countryservice.model.Country;
import org.bongz.countryservice.repository.CountryRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Profile("dev") // Disable this component during tests
@Component
public class DataLoader implements ApplicationRunner {

    private final CountryRepository countryRepository;
    private final RestTemplate restTemplate;

    public DataLoader(CountryRepository countryRepository, RestTemplate restTemplate) {
        this.countryRepository = countryRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Fetch data from the external API
        ApiCountry[] apiCountries = restTemplate.getForObject("https://restcountries.com/v3.1/all", ApiCountry[].class);

        if (apiCountries != null) {
            // Save data to the database
            Arrays.stream(apiCountries).forEach(apiCountry -> {
                // Extract the "common" name and the first capital
                String name = apiCountry.getName().getCommon(); // Extract "common" name
                String capital = (apiCountry.getCapital() != null && !apiCountry.getCapital().isEmpty())
                        ? apiCountry.getCapital().get(0)
                        : "N/A";

                // Create a simplified Country object
                Country country = new Country(
                        name, // Simplified: Directly use the "common" name
                        apiCountry.getFlag(),
                        apiCountry.getPopulation(),
                        capital // Simplified: Use only the first capital
                );

                // Save the country to the database
                countryRepository.save(country);
            });
        }
    }
}