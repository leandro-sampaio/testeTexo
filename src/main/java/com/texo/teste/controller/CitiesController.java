package com.texo.teste.controller;

import com.texo.teste.dto.ResultDTO;
import com.texo.teste.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/city")
public class CitiesController {

    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/capitais", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getCapitalCities(){
        return cityService.getCapitais();
    }

    @RequestMapping(value = "/cityByName", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getByName(){
        return null;
    }

    @RequestMapping(value = "/maiorMenorPorEstado", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getMaiorMenorPorEstado(){return null;}

    @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getPage(@RequestParam("page") int page, @RequestParam("size") int size){
        return cityService.getPage(page, size);}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ResultDTO> deleteCity(@PathVariable int id){
        return cityService.removeCity(id);
    }
}
