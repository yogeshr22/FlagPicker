package com.flagpicker.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.flagpicker.dto.CountryDTO;

@Component
public class FlagDAO {

  private Map<String, List<CountryDTO>> continents = new HashMap<>();
  private Map<String, String> fullCountries = new HashMap<>();
  private Map<String, Integer> countryMetrics = new LinkedHashMap<>();

  public void add(String continent, List<CountryDTO> countryDTOs) {
    continents.put(continent, countryDTOs);
    for (CountryDTO countryDTO : countryDTOs) {
      if (countryDTO.getName() != null && countryDTO.getName().trim().length() > 0 && countryDTO.getFlag() != null
          && countryDTO.getFlag().trim().length() > 0) {
        fullCountries.put(countryDTO.getName(), continent);
        countryMetrics.put(countryDTO.getName(), 0);
      }
    }
  }

  public List<CountryDTO> search(String keyword) {
    List<CountryDTO> returnList = new ArrayList<CountryDTO>();
    //provided search field is a continent
    if (continents.containsKey(keyword)) {
      return continents.get(keyword);
    }
    //provided search field is a country
    if (fullCountries.containsKey(keyword) && continents.containsKey(fullCountries.get(keyword))) {
      List<CountryDTO> countryDTOs = continents.get(fullCountries.get(keyword));
      for (CountryDTO countryDTO : countryDTOs) {
        if (countryDTO.getName().equals(keyword)) {
          returnList.add(countryDTO);
          countryDTO.setRecentViews(countryDTO.getRecentViews() + 1);
          countryMetrics.put(countryDTO.getName(), countryDTO.getRecentViews());
          break;
        }
      }
    }
    return returnList;
  }

  public Map<String, Integer> getCountryMetrics() {
    return countryMetrics;
  }

  public void setCountryMetrics(Map<String, Integer> countryMetrics) {
    this.countryMetrics = countryMetrics;
  }

}
