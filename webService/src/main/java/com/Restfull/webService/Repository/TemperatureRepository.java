package com.Restfull.webService.Repository;

import com.Restfull.webService.Model.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TemperatureRepository  extends JpaRepository<Temperature,Long> {
}
