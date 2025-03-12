package org.bongz.countryservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiCountry {
    private Name name;
    private List<String> capital;
    private String flag;
    private int population;

    @Data
    public static class Name {
        @JsonProperty("common")
        private String common;

        public String getCommon() {
            return common;
        }
    }

    public Name getName() {
        return name;
    }

    public List<String> getCapital() {
        return capital;
    }

    public String getFlag() {
        return flag;
    }

    public int getPopulation() {
        return population;
    }
}