package com.farhad.weatherRestfullApi.bootstrapData;


import com.farhad.weatherRestfullApi.model.Temperature;
import com.farhad.weatherRestfullApi.repository.TemperatureRepository;
import com.farhad.weatherRestfullApi.weatherDataApi.WeatherData;
import org.springframework.stereotype.Component;

@Component
public class BootstrapTemperature implements CommandLineRunner {
    TemperatureRepository temperatureRepository;
    WeatherData weather;

    public BootstrapTemperature(TemperatureRepository temperatureRepository, WeatherData weather) {
        this.temperatureRepository = temperatureRepository;
        this.weather = weather;
    }

    @Override
    public void run(String... args) throws Exception {
       String[] cities= {"tehran","karaj","shiraz","london"};
       for(int i=0;i<cities.length; i++){
           System.out.println(weather.getTempByCity(cities[i]));
           temperatureRepository.save(new Temperature(cities[i],weather.getTempByCity(cities[i])));
       }
    }
}
