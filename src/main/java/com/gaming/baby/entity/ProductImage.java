package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue
    private long id;


    @Column(unique = true, nullable = false, columnDefinition = "nvarchar(155)")
    private String src;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("images")
    private Product product;

    private Date datetime = new Date();

    public ProductImage() {
    }

    public ProductImage(Product product, String src){
        this.product = product;
        this.src = src;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }
}
