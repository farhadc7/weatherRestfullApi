package com.Restfull.webService.bootstrapData;

import com.Restfull.webService.Model.Temperature;
import com.Restfull.webService.Repository.TemperatureRepository;
import com.Restfull.webService.DataWeatherApi.DataWeather;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapTemperature implements CommandLineRunner {
    TemperatureRepository temperatureRepository;
    DataWeather weather;

    public BootstrapTemperature(TemperatureRepository temperatureRepository, DataWeather weather) {
        this.temperatureRepository = temperatureRepository;
        this.weather = weather;
    }

    @Override
    public void run(String... args) throws Exception {
       String[] cities= {"tehran","karaj","shiraz","london"};
       for(int i=0;i<cities.length; i++){

           temperatureRepository.save(new Temperature(cities[i],
                   weather.getTempByCity(cities[i]),
                   weather.getMinTempByCity(cities[i]),
                   weather.getMaxTempByCity(cities[i]),
                   weather.getPressureByCity(cities[i]),
                   weather.getHumidityByCity(cities[i])
           ));
       }
    }
}
