package com.gaming.baby.payload.request;


import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.Set;

public class AddNewVideoRequest {

    private long id = 0;

    @NotBlank
    private String name;

    @NotBlank
    private String src;
    private String cover;
    private String thumbnail;
    private String intro;

    private String actress;

    @NotBlank
    private double coin;

    private String categoryName;
    private Set<String> tagsName;

    private boolean display = false;

    public AddNewVideoRequest(@NotBlank String name, @NotBlank String src, String thumbnail, String intro, @NotBlank double coin, String categoryName, Set<String> tagsName) {
        this.name = name;
        this.src = src;
        this.thumbnail = thumbnail;
        this.intro = intro;
        this.coin = coin;
        this.categoryName = categoryName;
        this.tagsName = tagsName;
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getActress() {
        return actress;
    }

    public void setActress(String actress) {
        this.actress = actress;
    }
}
