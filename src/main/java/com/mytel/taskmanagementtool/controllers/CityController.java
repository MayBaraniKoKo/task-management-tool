package com.mytel.taskmanagementtool.controllers;

import com.mytel.taskmanagementtool.exceptions.ResourceNotFoundException;
import com.mytel.taskmanagementtool.models.City;
import com.mytel.taskmanagementtool.models.City;
import com.mytel.taskmanagementtool.models.Employee;
import com.mytel.taskmanagementtool.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.crypto.CksumType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    //list
    @GetMapping("/city")
    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    @GetMapping("/city/{city_id}")
    public ResponseEntity<City> getCityById(@PathVariable(value = "city_id") Long cityId)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + cityId));
        return ResponseEntity.ok().body(city);
    }

    //create
    @PostMapping("/city")
    public City createCity(@Validated @RequestBody City city) {
        return cityRepository.save(city);
    }

    //update
    @PutMapping("/city/{city_id}")
    public ResponseEntity<City> updateCity(@PathVariable(value = "city_id") Long cityId,
                                                    @Validated @RequestBody City cityDetail) throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + cityId));

        city.setCityName(cityDetail.getCityName());
        city.setPopulation(cityDetail.getPopulation());
        city.setSquare(cityDetail.getSquare());
        final City updateCity = cityRepository.save(city);
        return ResponseEntity.ok(updateCity);
    }

    //delete
    @DeleteMapping("/city/{city_id}")
    public Map<String, Boolean> deleteCity(@PathVariable(value = "city_id") Long cityId)
            throws ResourceNotFoundException {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new ResourceNotFoundException("City not found for this id :: " + cityId));

        cityRepository.delete(city);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
