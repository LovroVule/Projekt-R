package com.springdemo.projekt.domain;

import jakarta.persistence.*;

@Entity
public class Data_weather {

    @Id
    @GeneratedValue
    private Long data_weather_ID;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Lamp lamp;

    private double time;
    private double Temperature;
    private double Humidity;
    private double Visibility;
    private double Precipitation;

    public Long getData_weather_ID() {
        return data_weather_ID;
    }

    public Lamp getLamp() {
        return lamp;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }

    public double getVisibility() {
        return Visibility;
    }

    public void setVisibility(double visibility) {
        Visibility = visibility;
    }

    public double getPrecipitation() {
        return Precipitation;
    }

    public void setPrecipitation(double precipitation) {
        Precipitation = precipitation;
    }
}
