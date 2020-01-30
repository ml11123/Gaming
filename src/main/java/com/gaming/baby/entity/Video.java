package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Video {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JsonIgnoreProperties("videos")
    private VideoCategory category = new VideoCategory();

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("videos")
    private Set<VideoTag> tags = new HashSet<>();


    @Column(columnDefinition = "nvarchar(50)")
    private String src;
    private String thumbnail;
    private String cover;


    @Column(columnDefinition = "nvarchar(100)")
    private String name;
    private double coin;

    private String actor;

    @Column(columnDefinition = "text")
    private String intro;
    private boolean display;
    private Date datetime;

    public Video() {
    }

    public Video(String src, String name, double coin, String cover, String thumbnail, String intro, VideoCategory category, Set<VideoTag> tags) {
        this.src = src;
        this.name = name;
        this.coin = coin;
        this.thumbnail = thumbnail;
        this.tags = tags;
        this.cover = cover;
        this.category = category;


        this.intro = intro;
        this.display = false;

        this.setDatetime(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VideoCategory getCategory() {
        return category;
    }

    public void setCategory(VideoCategory category) {
        this.category = category;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<VideoTag> getTags() {
        return tags;
    }

    public void setTags(Set<VideoTag> tags) {
        this.tags = tags;
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

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
