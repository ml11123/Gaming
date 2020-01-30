package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class VideoTag {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true, columnDefinition = "nvarchar(50)")
    private String name;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties("tags")
    private Set<Video> videos;

    private Date datetime;

    public VideoTag() {
    }

    public VideoTag(String name) {
        this.name = name;
        this.setDatetime(new Date());
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

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }
}
