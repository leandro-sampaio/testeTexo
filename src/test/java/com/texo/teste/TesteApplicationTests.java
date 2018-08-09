package com.texo.teste;

import com.texo.teste.controller.CitiesController;
import com.texo.teste.dto.ResultDTO;
import com.texo.teste.entity.City;
import com.texo.teste.services.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@WebMvcTest(CitiesController.class)
public class TesteApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CitiesController citiesController;

	@MockBean
    private CityService cityService;


	@Test
	public void testGetCapitais() throws Exception {
        List<City> cities = this.getCities();

		given(citiesController.getCapitalCities()).willReturn(new ResponseEntity<>(new ResultDTO(cities.stream().filter(City::getCapital)), HttpStatus.OK));
		mockMvc.perform(get("/cities/capitais"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success[0].capital", is(true)));

	}

    @Test
    public void testGetCityByAttribute() throws Exception {
        List<City> cities = this.getCities();

		City city = new City(111, "PR", "Curitiba", true);

        given(citiesController.getByAttribute(city.getIbgeId(), city.getUf(), city.getName(), city.getCapital(), city.getLatitude(),
				city.getLongitude(), city.getNoAccents(), city.getAlternativeNames(), city.getMicroregion(), city.getMesoregion()))
				.willReturn(new ResponseEntity<>(new ResultDTO(cities.stream().filter(cityf -> cityf.getIbgeId() == 111)), HttpStatus.OK));
        mockMvc.perform(get("/cities/cityByAttribute"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetMaiorMenorPorEstado() throws Exception {
        List<City> cities = this.getCities();

        given(citiesController.getMaiorMenorPorEstado()).willReturn(new ResponseEntity<>(new ResultDTO(cities.stream().filter(city -> city.getIbgeId() == 111)), HttpStatus.OK));
        mockMvc.perform(get("/cities/maiorMenorPorEstado"))
                .andExpect(status().isOk());

    }

    @Test
    public void testGetPage() throws Exception {
        List<City> cities = this.getCities();

        int size = 5;
        int page = 1;
        int skip = size * (page - 1);
        given(citiesController.getPage(page, size)).willReturn(new ResponseEntity<>(new ResultDTO(cities.stream().skip(skip).limit(size)), HttpStatus.OK));
        mockMvc.perform(get("/cities/cityByAttribute"))
                .andExpect(status().isOk());

    }

    @Test
    public void testPutDeletar() throws Exception {
        City city = new City(111, "PR", "Curitiba", true);

        given(citiesController.deleteCity(city.getIbgeId())).willReturn(new ResponseEntity<>(new ResultDTO(), HttpStatus.OK));
        mockMvc.perform(put("/cities/deletar/"+city.getIbgeId()))
                .andExpect(status().isOk());

    }

	private List<City> getCities(){
		List<City> cities = new ArrayList<>();
		cities.add(new City(111, "PR", "Curitiba", true));
		cities.add(new City(112, "PR", "Londrina", false));
		cities.add(new City(113, "PR", "Arapongas", false));
		cities.add(new City(114, "SP", "São Paulo", true));
		cities.add(new City(115, "SP", "Campinas", false));
		cities.add(new City(116, "RS", "Porto Alegre", true));
		cities.add(new City(117, "SC", "Joinville", false));
		cities.add(new City(118, "SC", "Florianópolis", true));
		cities.add(new City(119, "SC", "Blumenau", true));
		return cities;
	}
}
