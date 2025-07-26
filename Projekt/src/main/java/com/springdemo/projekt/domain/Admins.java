package com.springdemo.projekt.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admins {

    @Id
    @GeneratedValue
    private Long admin_id;

    private String admin_name;
    private String admin_password;

    public String getAdmin_name() {
        return admin_name;
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    @Override
    public String toString() {
        return "Admins{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'';
    }
}
