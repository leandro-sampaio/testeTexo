package com.texo.teste.controller;

import com.texo.teste.dto.ResultDTO;
import com.texo.teste.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/cityByAttribute", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getByAttribute(@RequestParam(value = "ibgeId", required = false) Integer ibgeId, @RequestParam(value = "uf", required = false) String uf,
        @RequestParam(value = "name", required = false) String name, @RequestParam(value = "capital", required = false) Boolean capital,
        @RequestParam(value = "latitude", required = false) Double latitude, @RequestParam(value = "longitude", required = false) Double longitude,
        @RequestParam(value = "noAccents", required = false) String noAccents, @RequestParam(value = "alternativeNames", required = false) String alternativeNames,
        @RequestParam(value = "microregion", required = false) String microregion, @RequestParam(value = "mesoregion", required = false) String mesoregion){

        return cityService.getByAttribute(ibgeId, uf, name, capital, latitude, longitude,
                noAccents,alternativeNames, microregion, mesoregion);
    }

    @RequestMapping(value = "/maiorMenorPorEstado", method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getMaiorMenorPorEstado(){
        return cityService.getMaiorMenorPorEstado();
    }

    @RequestMapping(params = {"page", "size"}, method = RequestMethod.GET)
    public ResponseEntity<ResultDTO> getPage(@RequestParam("page") int page, @RequestParam("size") int size){
        return cityService.getPage(page, size);}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<ResultDTO> deleteCity(@PathVariable int id){
        return cityService.removeCity(id);
    }
}
