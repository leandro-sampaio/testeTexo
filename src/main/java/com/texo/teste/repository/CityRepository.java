package com.texo.teste.repository;

import com.texo.teste.entity.City;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CityRepository extends MongoRepository<City, String> {

    @Query("{'capital':?0}")
    List<City> getCapitais(boolean isCapital);

    @Query(value = "{'_id':?0}")
    City findCityById(int id);
}
