package com.flagpicker.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flagpicker.dto.ContinentDTO;
import com.flagpicker.dto.CountryDTO;
import com.flagpicker.repository.FlagRepository;
import com.flagpicker.service.FlagService;

@Service
public class FlagServiceImpl implements FlagService {

  @Autowired
  private FlagRepository flagRepository;

  @Override
  public void persistFlagDetails() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    List<ContinentDTO> continentList = mapper.readValue(new ClassPathResource("data/continents.json").getFile(),
        mapper.getTypeFactory().constructCollectionType(List.class, ContinentDTO.class));
    flagRepository.persistFlagDetails(continentList);

  }

  @Override
  public List<CountryDTO> searchFlag(String keyword) {
    return flagRepository.search(keyword);
  }

  @Override
  public Map<String, Integer> getCountryMetrics() {
    return flagRepository.getCountryMetrics();
  }

  @Override
  public List<ContinentDTO> getAllFlagDetails() {
    return flagRepository.allFlagDetails();
  }

}
