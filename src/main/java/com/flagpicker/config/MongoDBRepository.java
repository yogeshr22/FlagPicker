package com.flagpicker.config;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flagpicker.dto.ContinentDTO;

@Repository
public interface MongoDBRepository extends MongoRepository<ContinentDTO, String> {

}
