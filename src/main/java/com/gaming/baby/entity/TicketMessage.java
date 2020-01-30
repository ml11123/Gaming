package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class TicketMessage {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("messages")
    private Ticket ticket;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("tickets")
    private Users author;

    @Column(columnDefinition = "ntext")
    private String message;

    private Date replied_time;

    public TicketMessage() {
    }

    public TicketMessage(Ticket ticket, Users author, String message){
        this.ticket = ticket;
        this.message = message;
        this.author = author;
        this.setReplied_time(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getReplied_time() {
        return replied_time;
    }

    public void setReplied_time(Date replied_time) {
        this.replied_time = replied_time;
    }

    public Users getAuthor() {
        return author;
    }

    public void setAuthor(Users author) {
        this.author = author;
    }
}
