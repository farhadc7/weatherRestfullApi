package com.Restfull.webService.Services;

import com.Restfull.webService.Model.Temperature;
import com.Restfull.webService.Repository.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatureServices {
    TemperatureRepository repository;

    public TemperatureServices(TemperatureRepository repository) {
        this.repository = repository;
    }

    public Optional<Temperature> findByCity(String city){

        List<Temperature> temps= repository.findAll();
        System.out.println(temps.size());
        for(Temperature t : temps){
            t.getCity();
            if(t.getCity().equals(city)){
                System.out.println(t.getCity()+" winner");
                return Optional.ofNullable(t);
            }
        }
        return Optional.empty();
    }
}
