package org.example.redis;

import lombok.Getter;
import lombok.Setter;
import org.example.domain.Continent;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
public class CityCountry {
    private Integer id;

    private String name;

    private String district;

    private Integer population;

    private String countryCode;

    private String alternativeCountryCode;

    private String countryName;

    private Continent continent;

    private String countryRegion;

    private BigDecimal countrySurfaceArea;

    private Integer countryPopulation;

    private Set<Language> languages;
}