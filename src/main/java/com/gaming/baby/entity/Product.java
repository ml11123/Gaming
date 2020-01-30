package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties("products")
    private ProductCategory category = new ProductCategory();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("videos")
    private Set<ProductTag> tags = new HashSet<>();

    @Column(columnDefinition = "nvarchar(50)")
    private String thumbnail;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("product")
    private Set<ProductImage> images = new HashSet<>();

    @Column(columnDefinition = "nvarchar(50)")
    private String name;

    private Date datetime = new Date();

    private double coin;

    @Column(columnDefinition = "ntext")
    private String intro;

    private boolean display = false;

    public Product() {
    }

    public Product(String name, double coin, String thumbnail, String intro, ProductCategory category, Set<ProductTag> tags) {
        this.name = name;
        this.coin = coin;
        this.tags = tags;
        this.category = category;
        this.thumbnail = thumbnail;
        this.intro = intro;

        this.setDatetime(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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

    public double getCoin() {
        return coin;
    }

    public void setCoin(double coin) {
        this.coin = coin;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Set<ProductTag> getTags() {
        return tags;
    }

    public void setTags(Set<ProductTag> tags) {
        this.tags = tags;
    }

    public Set<ProductImage> getImages() {
        return images;
    }

    public void setImages(Set<ProductImage> images) {
        this.images = images;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
