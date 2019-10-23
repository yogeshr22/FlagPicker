package com.flagpicker.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.flagpicker.dto.ContinentDTO;
import com.flagpicker.dto.CountryDTO;

public interface FlagService {

  public void persistFlagDetails() throws IOException;

  public List<ContinentDTO> getAllFlagDetails();

  public List<CountryDTO> searchFlag(String keyword);

  public Map<String, Integer> getCountryMetrics();

}
