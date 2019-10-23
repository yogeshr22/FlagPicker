package com.flagpicker.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.flagpicker.config.MongoDBRepository;
import com.flagpicker.dto.ContinentDTO;
import com.flagpicker.dto.CountryDTO;
import com.flagpicker.repository.FlagRepository;

@Repository
public class FlagRepositoryImpl implements FlagRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(FlagRepositoryImpl.class);

  private Map<String, List<CountryDTO>> continents = new HashMap<>();
  private Map<String, String> fullCountries = new HashMap<>();
  private Map<String, Integer> countryMetrics = new LinkedHashMap<>();
  private List<ContinentDTO> continentList = new ArrayList<>();

  @Value("${db.collection.name}")
  private String collectionName;

  @Autowired
  private MongoDBRepository dbRepository;

  @Override
  public void persistFlagDetails(List<ContinentDTO> continentDTOs) {
    LOGGER.debug("Initilializing JSON Repository");
    //During the Boot up, clear all the records
    dbRepository.deleteAll();
    dbRepository.saveAll(continentDTOs);
  }

  private void loadFlagDetails() {
    if (continentList == null || continentList.size() == 0) {
      continentList = dbRepository.findAll();
      initializeCollection();
    }
  }

  private void initializeCollection() {
    if (continentList != null && continentList.size() > 0)
      continentList.forEach(dto -> {
        if (dto.getContinent() != null && dto.getCountries() != null && dto.getCountries().size() > 0)
          continents.put(dto.getContinent(), dto.getCountries());
        dto.getCountries().forEach(countryDTO -> {
          if (countryDTO.getName() != null && countryDTO.getName().trim().length() > 0 && countryDTO.getFlag() != null
              && countryDTO.getFlag().trim().length() > 0) {
            fullCountries.put(countryDTO.getName(), dto.getContinent());
            countryMetrics.put(countryDTO.getName(), 0);
          }
        });
      });

  }

  @Override
  public List<CountryDTO> search(String keyword) {
    loadFlagDetails();
    List<CountryDTO> returnList = new ArrayList<CountryDTO>();
    //provided search field is a continent
    if (continents.containsKey(keyword)) {
      LOGGER.debug("Provided Search Field is a Continent");
      return continents.get(keyword);
    }
    //provided search field is a country
    if (fullCountries.containsKey(keyword) && continents.containsKey(fullCountries.get(keyword))) {
      LOGGER.debug("Provided Search Field is a Country");
      for (CountryDTO countryDTO : continents.get(fullCountries.get(keyword))) {
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

  @Override
  public Map<String, Integer> getCountryMetrics() {
    loadFlagDetails();
    return countryMetrics;
  }

  @Override
  public List<ContinentDTO> allFlagDetails() {
    loadFlagDetails();
    return continentList;
  }

}
