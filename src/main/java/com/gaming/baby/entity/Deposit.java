package com.gaming.baby.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Deposit {

    @Id
    @GeneratedValue
    private long id;

    private double amount;

    private double cash;

    @ManyToOne(optional = false)
    private Users user;

    @Column(columnDefinition = "ntext")
    private String notes;

    @Column(columnDefinition = "datetime default getdate()")
    private Date datetime;

    @PrePersist
    public void autofill() {
        this.setDatetime(new Date());
    }

    public Deposit() {
    }

    public Deposit(Users user, double amount, double cash, String notes) {
        this.user = user;
        this.amount = amount;
        this.cash = cash;
        this.notes = notes;
    }

    public Deposit(double amount, double cash, String notes) {
        this.amount = amount;
        this.cash = cash;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
