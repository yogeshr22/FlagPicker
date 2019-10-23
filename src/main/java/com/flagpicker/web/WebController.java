package com.flagpicker.web;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flagpicker.config.MongoDBRepository;
import com.flagpicker.dto.ContinentDTO;
import com.flagpicker.dto.CountryDTO;
import com.flagpicker.dto.ResponseDTO;
import com.flagpicker.service.FlagService;
import com.flagpicker.utils.Constants.ReturnCode;

@RestController
@RequestMapping("/api/")
public class WebController {

  private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);

  @Autowired
  private FlagService flagService;
  
  @Autowired
  private MongoDBRepository dbRepository;

  @PostConstruct
  private void persistFlagDetails() {
    try {
      flagService.persistFlagDetails();
    } catch (Exception e) {
      LOGGER.error("Error occured! while persisting the Flag Details!", e);
    }
  }

  /**
   * Return all the Flag details
   *   
   */
  @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
  public ResponseDTO<List<ContinentDTO>> getAllFlagDetails() {
    ResponseDTO<List<ContinentDTO>> responseDTO = new ResponseDTO<>();
    try {
      LOGGER.info("Request received to collect the flag details!!");
      responseDTO.setResponse(flagService.getAllFlagDetails());
      responseDTO.setReturnCode(ReturnCode.SUCCESS);
    } catch (Exception e) {
      responseDTO.setReturnCode(ReturnCode.ERROR);
      LOGGER.error("Error Occured! {}", e);
    }
    return responseDTO;
  }

  /**
   *  Search the Flag Repo and returns the appropriate results. The search is generic for both continent and country.
   *   @param keyword
   */
  @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")
  public ResponseDTO<List<CountryDTO>> search(@RequestParam String keyword) {
    ResponseDTO<List<CountryDTO>> responseDTO = new ResponseDTO<>();
    try {
      LOGGER.info("Request received to search the flag details!!");
      List<CountryDTO> searchResult = flagService.searchFlag(keyword);
      if (searchResult != null && searchResult.size() > 0) {
        responseDTO.setResponse(searchResult);
        responseDTO.setReturnCode(ReturnCode.SUCCESS);
      } else
        responseDTO.setReturnCode(ReturnCode.DATANOTFOUND);
    } catch (Exception e) {
      responseDTO.setReturnCode(ReturnCode.ERROR);
      LOGGER.error("Error Occured! {}", e);
    }
    return responseDTO;
  }

  /**
   * Returns the Flag Metrics
   *   
   */
  @RequestMapping(value = "/metrics", method = RequestMethod.GET, produces = "application/json")
  public ResponseDTO<Map<String, Integer>> getMetrics() {
    ResponseDTO<Map<String, Integer>> responseDTO = new ResponseDTO<>();
    try {
      LOGGER.info("Request received to get the Metrics!!");
      Map<String, Integer> countryMetrics = flagService.getCountryMetrics();
      if (countryMetrics != null && countryMetrics.size() > 0) {
        Map<String, Integer> sortedMap = countryMetrics.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        responseDTO.setResponse(sortedMap);
        responseDTO.setReturnCode(ReturnCode.SUCCESS);
      } else
        responseDTO.setReturnCode(ReturnCode.DATANOTFOUND);
    } catch (Exception e) {
      responseDTO.setReturnCode(ReturnCode.ERROR);
      LOGGER.error("Error Occured! {}", e);
    }
    return responseDTO;
  }

}