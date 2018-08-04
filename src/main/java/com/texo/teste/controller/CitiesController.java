package com.texo.teste.controller;

import com.texo.teste.entity.City;
import com.texo.teste.repository.CityRepository;
import com.texo.teste.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CitiesController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/capitais", method = RequestMethod.GET)
    public List<City> getCapitalCities(){
        return cityService.findCapitais();
    }
}
