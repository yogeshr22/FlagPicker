package com.flagpicker.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flagpicker.dao.FlagDAO;
import com.flagpicker.dto.CountryDTO;
import com.flagpicker.dto.FlagDTO;

@Service
public class FlagService {

  @Autowired
  private FlagDAO flagDAO;

  public void processFlagDetails(List<FlagDTO> flagDTOList) {
    if (flagDTOList != null && flagDTOList.size() > 0)
      for (FlagDTO dto : flagDTOList) {
        if (dto.getContinent() != null && dto.getCountries() != null && dto.getCountries().size() > 0)
          flagDAO.add(dto.getContinent(), dto.getCountries());
      }
  }

  public List<CountryDTO> searchFlag(String keyword) {
    return flagDAO.search(keyword);
  }

  public Map<String, Integer> getCountryMetrics() {
    return flagDAO.getCountryMetrics();
  }

}
