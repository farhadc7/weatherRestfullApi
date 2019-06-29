package com.Restfull.webService.controllers;

import com.Restfull.webService.Model.Temperature;
import com.Restfull.webService.Services.TemperatureServices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(TemperatureControllerRest.base_url)
public class TemperatureControllerRest {
    private final TemperatureServices temperatureServices;
    public final static String base_url="api/v1/city";

    public TemperatureControllerRest(TemperatureServices temperatureServices) {
        this.temperatureServices = temperatureServices;
    }

    @GetMapping("/{city}")
    public Temperature findTemperature(@PathVariable(name = "city") String city){
        Temperature temp=new Temperature();
        Optional<Temperature> opt=temperatureServices.findByCity(city);
        if(opt.isPresent()){
            temp= opt.get();
        }else{
            temp.setCity("unknown");
            temp.setTemp("10000");
        }
        return temp;
    }
}
