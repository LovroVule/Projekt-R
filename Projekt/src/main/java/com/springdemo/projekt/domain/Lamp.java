package com.springdemo.projekt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Lamp {
    @Id
    private Long LampId;
    private Double usage;
    private Double workHours;
    private String addres;

    @OneToOne(mappedBy = "lamp")
    private Location location;

    @OneToMany(mappedBy = "lamp")
    private Data_traffic data_traffic;

    @OneToMany(mappedBy = "lamp")
    private Data_people data_people;

    @OneToMany(mappedBy = "lamp")
    private Data_weather data_weather;

    @OneToMany(mappedBy = "lamp")
    private Data_dim data_dim;

    public Long getLampId() {return LampId;}

    public void setLampId(Long lampId) {LampId = lampId;}

    public Double getUsage() {return usage;}

    public void setUsage(Double usage) {this.usage = usage;}

    public Double getWorkHours() {return workHours;}

    public void setWorkHours(Double workHours) {this.workHours = workHours;}

    public String getAddres() {return addres;}

    public void setAddres(String addres) {this.addres = addres;}

    @Override
    public String toString() {
        return "Lamp{" +
                "LampId=" + LampId +
                ", usage=" + usage +
                ", workHours=" + workHours +
                ", addres='" + addres + '\'' +
                '}';
    }
}
