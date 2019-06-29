package com.farhad.weatherRestfullApi.repository;

import com.farhad.weatherRestfullApi.model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TemperatureRepository extends JpaRepository<Temperature,Long> {
}
