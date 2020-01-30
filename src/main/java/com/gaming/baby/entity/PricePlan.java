package com.gaming.baby.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PricePlan {

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    private int type;

    private double credit;

    private double cash;

    @Column(columnDefinition = "varchar(155) default null")
    private String image;

    public PricePlan() {
    }

    public PricePlan(String name, int type, double credit, double cash, String image) {
        this.name = name;
        this.type = type;
        this.credit = credit;
        this.cash = cash;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
