package com.flagpicker.service.test.unit;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.flagpicker.dao.FlagDAO;
import com.flagpicker.dto.CountryDTO;
import com.flagpicker.dto.FlagDTO;
import com.flagpicker.service.FlagService;

@RunWith(MockitoJUnitRunner.class)
public class FlagServiceUnitTest {

  @Mock
  FlagService flagServiceMock;

  @Mock
  FlagDAO flagDAOMock;

  @Test
  public void getCountryMetricsTest() {
    when(flagServiceMock.getCountryMetrics()).thenReturn(new LinkedHashMap<>());
  }

  @Test
  public void processFlagDetailsTest() {
    flagServiceMock.processFlagDetails(getFlagDTOList());
  }

  @Test
  public void searchFlagTest() {
    when(flagServiceMock.searchFlag("Nigeria")).thenReturn(new ArrayList<CountryDTO>());
  }

  private List<FlagDTO> getFlagDTOList() {
    List<FlagDTO> flagDTOList = new ArrayList<>();
    FlagDTO flagDTO = new FlagDTO();
    flagDTO.setContinent("Africa");
    flagDTOList.add(flagDTO);
    CountryDTO countryDTO = new CountryDTO();
    countryDTO.setName("Nigeria");
    countryDTO.setFlag("XXXXX");
    List<CountryDTO> countryDTOs = new ArrayList<>();
    countryDTOs.add(countryDTO);
    flagDTO.setCountries(countryDTOs);
    return flagDTOList;
  }

}
