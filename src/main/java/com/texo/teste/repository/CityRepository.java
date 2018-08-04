package com.texo.teste.repository;

import com.texo.teste.entity.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CityRepository extends MongoRepository<City, String> {

    @Query("{'capital':true}")
    List<City> findCapitais();
}
