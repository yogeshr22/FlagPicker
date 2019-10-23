package com.flagpicker.repository;

import java.util.List;
import java.util.Map;

import com.flagpicker.dto.CountryDTO;
import com.flagpicker.dto.ContinentDTO;

public interface FlagRepository {

  public void persistFlagDetails(List<ContinentDTO> continentDTOs);

  public List<ContinentDTO> allFlagDetails();

  public List<CountryDTO> search(String keyword);

  public Map<String, Integer> getCountryMetrics();

}
