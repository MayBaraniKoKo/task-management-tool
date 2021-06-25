package com.mytel.taskmanagementtool.repositories;

import com.mytel.taskmanagementtool.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City,Long> {

}


