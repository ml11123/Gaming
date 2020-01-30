package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JsonIgnoreProperties("tickets")
    private Users user;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    private String issue;

    @Column(columnDefinition = "ntext")
    private String description;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int type = 0;

    @OneToMany(mappedBy = "ticket")
    @JsonIgnoreProperties("ticket")
    private List<TicketMessage> messages;

    private Date datetime;

    private Date updateTime;


    public Ticket() {
    }

    public Ticket(Users user, String issue, String description){
        this.user = user;
        this.issue = issue;
        this.description = description;

        this.setDatetime(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser(){
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public List<TicketMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<TicketMessage> messages) {
        this.messages = messages;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
