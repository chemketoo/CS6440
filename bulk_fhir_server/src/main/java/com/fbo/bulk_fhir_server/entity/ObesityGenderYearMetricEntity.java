package com.fbo.bulk_fhir_server.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "obesity_metrics_gender_year")
public class ObesityGenderYearMetricEntity {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "gender")
    private String gender;

    @Column(name = "year")
    private int year;

    @Column(name = "age")
    private String age;

    @Column(name = "underweight")
    private int underweight;

    @Column(name = "healthy")
    private int healthy;

    @Column(name = "overweight")
    private int overweight;

    @Column(name = "obese")
    private int obese;

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
