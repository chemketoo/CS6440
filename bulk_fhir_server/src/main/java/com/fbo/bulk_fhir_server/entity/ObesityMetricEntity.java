package com.fbo.bulk_fhir_server.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "obesity_metrics")
public class ObesityMetricEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "gender")
    private String gender;

    @Column(name = "underweight")
    private int underweight;

    @Column(name = "healthy")
    private int healthy;

    @Column(name = "overweight")
    private int overweight;

    @Column(name = "obese")
    private int obese;

    @Column(name = "last_updated")
    @Temporal(TemporalType.DATE)
    private Date last_updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getUnderweight() {
        return underweight;
    }

    public void setUnderweight(int underweight) {
        this.underweight = underweight;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }

    public int getOverweight() {
        return overweight;
    }

    public void setOverweight(int overweight) {
        this.overweight = overweight;
    }

    public int getObese() {
        return obese;
    }

    public void setObese(int obese) {
        this.obese = obese;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }
}