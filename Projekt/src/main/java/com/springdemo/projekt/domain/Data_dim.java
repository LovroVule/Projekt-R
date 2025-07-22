package com.springdemo.projekt.domain;

import jakarta.persistence.*;

@Entity
public class Data_dim {

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Lamp lamp;

    @Id
    @GeneratedValue
    private Long data_dim_id;

    private double time;
    private double Dim_king;
    private double Dim_jjs;
    private double Dim_trg;
    private double Dimm_correction;

    public Long getData_dim_id() {
        return data_dim_id;
    }

    public double getDim_king() {
        return Dim_king;
    }

    public void setDim_king(double dim_king) {
        Dim_king = dim_king;
    }

    public double getDim_jjs() {
        return Dim_jjs;
    }

    public void setDim_jjs(double dim_jjs) {
        Dim_jjs = dim_jjs;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getDim_trg() {
        return Dim_trg;
    }

    public void setDim_trg(double dim_trg) {
        Dim_trg = dim_trg;
    }

    public double getDimm_correction() {
        return Dimm_correction;
    }

    public void setDimm_correction(double dimm_correction) {
        Dimm_correction = dimm_correction;
    }

    public Lamp getLamp() {
        return lamp;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }
}
