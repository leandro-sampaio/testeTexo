package com.texo.teste.services;

import com.texo.teste.dto.ErroDTO;
import com.texo.teste.dto.ResultDTO;
import com.texo.teste.entity.City;
import com.texo.teste.repository.CityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityService {

    private static final Logger LOGGER = LogManager.getLogger(CityService.class);

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity<ResultDTO> getCapitais(){
       List<City> cities = cityRepository.getCapitais(true);
       if(cities == null){
           return new ResponseEntity<>(new ResultDTO(new ErroDTO(HttpStatus.BAD_REQUEST.value(), "Nenhuma capital encontrada.")), HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(new ResultDTO(cities.size(), cities), HttpStatus.OK);
    }

    public ResponseEntity<ResultDTO> removeCity(int id) {
        City city = cityRepository.findCityById(id);
        if(city == null){
            return new ResponseEntity<>(new ResultDTO(new ErroDTO(HttpStatus.BAD_REQUEST.value(), String.format("Nenhuma cidade encontrada com o código %s.", id))), HttpStatus.BAD_REQUEST);
        }
        cityRepository.delete(city);
        return new ResponseEntity<>(new ResultDTO(), HttpStatus.OK);
    }

    public ResponseEntity<ResultDTO> getPage(int page, int size) {
        int skip = size * (page - 1);
        Query query = new Query();
        query.skip(skip);
        query.limit(size);
        query.with(new Sort(Sort.Direction.ASC, "name"));
        List<City> citiesPagination = mongoTemplate.find(query, City.class);
        if(citiesPagination.size() == 0){
            return new ResponseEntity<>(new ResultDTO(new ErroDTO(HttpStatus.BAD_REQUEST.value(), "página encontrada.")), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResultDTO(citiesPagination.size(), citiesPagination), HttpStatus.OK);
    }

    public ResponseEntity<ResultDTO> getByAttribute(Integer ibgeId, String uf, String name, Boolean capital,
                                                    Double latitude, Double longitude, String noAccents,
                                                    String alternativeNames, String microregion, String mesoregion) {
        List<City> cities = null;
        Query query = new Query();
        if(ibgeId != null){
            query.addCriteria(Criteria.where("ibgeId").regex(ibgeId.toString()));
        }else if(uf != null){
            query.addCriteria(Criteria.where("uf").regex(uf));
        }else if(name != null){
            query.addCriteria(Criteria.where("name").regex(name));
        }else if(capital != null){
            cities = cityRepository.getCapitais(capital);
        }else if(latitude != null){
            query.addCriteria(Criteria.where("latitude").regex(latitude.toString()));
        }else if(longitude != null){
            query.addCriteria(Criteria.where("longitude").regex(longitude.toString()));
        }else if(noAccents != null){
            query.addCriteria(Criteria.where("name").regex(noAccents));
        }else if(alternativeNames != null){
            query.addCriteria(Criteria.where("name").regex(alternativeNames));
        }else if(microregion != null){
            query.addCriteria(Criteria.where("name").regex(microregion));
        }else if(mesoregion != null){
            query.addCriteria(Criteria.where("name").regex(mesoregion));
        }
        if(cities == null) {
            cities = mongoTemplate.find(query, City.class);
        }
        if(cities.size() == 0){
            return new ResponseEntity<>(new ResultDTO(new ErroDTO(HttpStatus.BAD_REQUEST.value(), String.format("Nenhuma cidade encontrada."))), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResultDTO(cities.size(), cities), HttpStatus.OK);
    }
}
