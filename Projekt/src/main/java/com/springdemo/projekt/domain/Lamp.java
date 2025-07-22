package com.springdemo.projekt.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Lamp {

    @Id
    @GeneratedValue
    private Long id;
    private Double usage;
    private Double workHours;
    private String addres;
    private int status;

    public int getStatus() {return status;}

    public void setStatus(int status) {this.status = status;}

    @OneToOne(mappedBy = "lamp")
    private Location location;

    @OneToMany(mappedBy = "lamp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data_traffic> data_traffic = new ArrayList<Data_traffic>();

    @OneToMany(mappedBy = "lamp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data_people> data_people = new ArrayList<>();

    @OneToMany(mappedBy = "lamp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data_weather> data_weather = new ArrayList<>();

    @OneToMany(mappedBy = "lamp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data_dim> data_dim = new ArrayList<>();

    public Long getLampId() {return id;}


    public Double getUsage() {return usage;}

    public void setUsage(Double usage) {this.usage = usage;}

    public Double getWorkHours() {return workHours;}

    public void setWorkHours(Double workHours) {this.workHours = workHours;}

    public String getAddres() {return addres;}

    public void setAddres(String addres) {this.addres = addres;}

    @Override
    public String toString() {
        return "Lamp{" +
                "LampId=" + id +
                ", usage=" + usage +
                ", workHours=" + workHours +
                ", addres='" + addres + '\'' +
                '}';
    }
}
