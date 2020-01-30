package com.gaming.baby.payload.request;

import java.util.Set;

public class AddProductRequest {

    private long id = 0;

    private String name;
    private String categoryName;
    private Set<String> tagsName;
    private String thumbnail;
    private Set<String> imagesSrc;
    private String intro;
    private double coin;
    private boolean display = false;


    public AddProductRequest(String name, String categoryName, Set<String> tagsName, String thumbnail, Set<String> imagesSrc, String intro, double coin) {
        this.name = name;
        this.categoryName = categoryName;
        this.tagsName = tagsName;
        this.thumbnail = thumbnail;
        this.imagesSrc = imagesSrc;
        this.intro = intro;
        this.coin = coin;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(Set<String> tagsName) {
        this.tagsName = tagsName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Set<String> getImagesSrc() {
        return imagesSrc;
    }

    public void setImagesSrc(Set<String> imagesSrc) {
        this.imagesSrc = imagesSrc;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public double getCoin() {
        return coin;
    }

    public void setCoin(double coin) {
        this.coin = coin;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }
}
