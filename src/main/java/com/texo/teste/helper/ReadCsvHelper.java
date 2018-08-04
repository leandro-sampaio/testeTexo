package com.texo.teste.helper;

import com.texo.teste.entity.City;
import com.texo.teste.repository.CityRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ReadCsvHelper {

    @Autowired
    private CityRepository cityRepository;

    @Value("${path.file.csv}")
    private String pathCsv;

    private static final Logger LOGGER = LogManager.getLogger(ReadCsvHelper.class);

    public ReadCsvHelper() {
    }

    @PostConstruct
    public void importCsv(){
        try {
            File file = new File(pathCsv);
            if(!file.exists()){
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("File CSV not found");
                }
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            Scanner scanner = null;
            int index = 0;

            while ((line = reader.readLine()) != null){
                City city = new City();
                scanner = new Scanner(line);
                scanner.useDelimiter(",");
                while (scanner.hasNext()){
                    String data = scanner.next();
                    if(index == 0) {
                        if(data == null)continue;
                        if(!isNumeric(data))continue;
                        city.setIbgeId(Integer.parseInt(data));
                    }else if(index == 1){
                        city.setUf(data);
                    }else if(index == 2){
                        city.setName(data);
                    }else if(index == 3){
                        city.setCapital(Boolean.parseBoolean(data));
                    }else if(index == 4){
                        city.setLongitude(data != null ? Double.parseDouble(data) : 0.0);
                    }else if(index == 5){
                        city.setLatitude(data != null ? Double.parseDouble(data) : 0.0);
                    }else if(index == 6){
                        city.setNoAccents(data);
                    }else if(index == 7){
                        city.setAlternativeNames(data);
                    }else if(index == 8){
                        city.setMicroregion(data);
                    }else if(index == 9){
                        city.setMesoregion(data);
                    }

                    index++;
                }
                index = 0;
                if(city.getIbgeId() > 0) {
                    cityRepository.save(city);
                }
            }

            reader.close();

        } catch (Exception e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    private boolean isNumeric(String data){
        String regex = "^(-?[1-9]+\\d*)$|^0$";
        return data.matches(regex);
    }
}
