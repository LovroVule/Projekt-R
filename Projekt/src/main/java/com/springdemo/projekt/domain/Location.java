package com.springdemo.projekt.domain;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private double latitude;
    private double longitude;

    @OneToOne
    @JoinColumn(name = "lamp_id", referencedColumnName = "id")
    private Lamp lamp;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public double getLatitude() {return latitude;}

    public void setLatitude(double latitude) {this.latitude = latitude;}

    public double getLongitude() {return longitude;}

    public void setLongitude(double longitude) {this.longitude = longitude;}

    public Lamp getLamp() {return lamp;}

    public void setLamp(Lamp lamp) {this.lamp = lamp;}

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", lamp=" + lamp +
                '}';
    }
}
