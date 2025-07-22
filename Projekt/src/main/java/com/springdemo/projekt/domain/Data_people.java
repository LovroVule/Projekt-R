package com.springdemo.projekt.domain;

import jakarta.persistence.*;

@Entity
public class Data_people {

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Lamp lamp;

    @Id
    @GeneratedValue
    private Long data_people_ID;

    private double time;
    private double people_on_street;
    private double old_people_on_street;
    private double young_people_on_street;
    private double people_on_street_dissability;

    public Lamp getLamp() {
        return lamp;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    public Long getData_people_ID() {
        return data_people_ID;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPeople_on_street() {
        return people_on_street;
    }

    public void setPeople_on_street(double people_on_street) {
        this.people_on_street = people_on_street;
    }

    public double getOld_people_on_street() {
        return old_people_on_street;
    }

    public void setOld_people_on_street(double old_people_on_street) {
        this.old_people_on_street = old_people_on_street;
    }

    public double getYoung_people_on_street() {
        return young_people_on_street;
    }

    public void setYoung_people_on_street(double young_people_on_street) {
        this.young_people_on_street = young_people_on_street;
    }

    public double getPeople_on_street_dissability() {
        return people_on_street_dissability;
    }

    public void setPeople_on_street_dissability(double people_on_street_dissability) {
        this.people_on_street_dissability = people_on_street_dissability;
    }
}
