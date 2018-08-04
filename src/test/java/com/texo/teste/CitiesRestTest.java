package com.texo.teste;

import com.texo.teste.controller.CitiesController;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CitiesRestTest extends TesteApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private CitiesController citiesController;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(citiesController).build();
    }
}
