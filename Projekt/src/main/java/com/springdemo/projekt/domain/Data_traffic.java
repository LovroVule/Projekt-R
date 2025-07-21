package com.springdemo.projekt.domain;

import jakarta.persistence.Entity;

@Entity
public class Data_traffic {

    private Long data_traffic_id;

    public void setData_traffic_id(Long dataTrafficId) {this.data_traffic_id = dataTrafficId;}

    public Long getData_traffic_id() {return data_traffic_id;}
}
