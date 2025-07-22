package com.springdemo.projekt.domain;

import jakarta.persistence.*;

@Entity
public class Data_traffic {

    @Id
    @GeneratedValue
    private Long data_traffic_id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Lamp lamp;

    private double time;
    private double jam_factor;

    public Long getData_traffic_id() {return data_traffic_id;}

    public Lamp getLamp() {return lamp;}

    public double getTime() {return time;}

    public double getJam_factor() {return jam_factor;}

    public void setData_traffic_id(Long data_traffic_id) {
        this.data_traffic_id = data_traffic_id;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
