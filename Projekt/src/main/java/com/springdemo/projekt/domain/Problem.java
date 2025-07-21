package com.springdemo.projekt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
public class Problem {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String adress;
    private String phoneNumber;
    private String description;
    private Timestamp createdOn;

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn;
    }
}
