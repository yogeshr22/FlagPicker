package com.flagpicker.dto;

import java.util.List;

public class FlagDTO {

  private String continent;
  private List<CountryDTO> countries;

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public List<CountryDTO> getCountries() {
    return countries;
  }

  public void setCountries(List<CountryDTO> countries) {
    this.countries = countries;
  }

}
