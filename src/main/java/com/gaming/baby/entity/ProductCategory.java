package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductCategory {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private Set<Product> products;

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
