package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ProductTag {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, columnDefinition = "nvarchar(50)")
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("tags")
    private Set<Product> products = new HashSet<>();

    private Date datetime;

    public ProductTag(String name) {
        this.name = name;
        this.setDatetime(new Date());
    }

    public ProductTag() {

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

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
