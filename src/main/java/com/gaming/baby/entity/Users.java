package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Users {


    @Id
    @GeneratedValue
    private long id;

    @Column(unique=true, nullable = false, updatable = false, columnDefinition = "varchar(10)")
    private String UID;

    @Column(unique = true, nullable = false, updatable = false, columnDefinition = "nvarchar(20)")
    private String username;

    @Column(columnDefinition = "nvarchar(50) default null")
    private String display_name;

    @Column(columnDefinition = "varchar(15)")
    private String phone;

    @Column(columnDefinition = "varchar(50)")
    private String email;

    @Column(columnDefinition = "varchar(255) default null")
    private String avatar;

    @Column(columnDefinition = "int default 0")
    private int game_progress;

    @Column(columnDefinition = "varchar(50) default null")
    private String address;

    @Column(columnDefinition = "int default 0")
    private int status;

    @Column(unique=true, nullable = false, columnDefinition = "varchar(60)")
    @JsonIgnore
    private String password;

    @Column(nullable = false, columnDefinition = "bit default 0")
    private boolean vip;

    @Column(columnDefinition = "varchar(20) default null")
    @JsonIgnore
    private String token;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<Deposit> deposits;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private Set<Ticket> tickets;

    @Transient
    private double totalCredit;

    @Column(columnDefinition = "datetime default getdate()")
    private Date datetime;

    @JsonIgnore
    public String getDecodedToken(){
        return new String(Base64.getDecoder().decode(this.getToken().getBytes()));
    }

    @PrePersist
    public void autofill() {
        Random rand = new Random();
        this.setUID(String.format("%010d", rand.nextInt(1000000000)) );
        this.setToken(Base64.getEncoder().encodeToString(String.format("%04d", rand.nextInt(10000)).getBytes()));
        this.setDatetime(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGame_progress() {
        return game_progress;
    }

    public void setGame_progress(int game_progress) {
        this.game_progress = game_progress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    public double getTotalCredit(){
        for (Deposit deposit : this.deposits) {
            totalCredit += deposit.getAmount();
        }

        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
