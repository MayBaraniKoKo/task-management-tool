package com.mytel.taskmanagementtool.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TMS_City",uniqueConstraints = @UniqueConstraint(columnNames = {"city_id"}))
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "city_seq")
    @SequenceGenerator(name = "city_seq",allocationSize = 1,sequenceName = "city_seq")
    private Long city_id;

    @Column(name = "city_name")
    private String name;

    @Column(name = "population")
    private int population;

    @Column(name = "square")
    private double square;

    public City(){

    }

    public City(Long city_id, String name, int population, double square) {
        this.city_id = city_id;
        this.name = name;
        this.population = population;
        this.square = square;
    }

    public Long getCity_id() {
        return city_id;
    }

    public void setCity_id(Long city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }


}
