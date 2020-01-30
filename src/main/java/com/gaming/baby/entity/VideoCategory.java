package com.gaming.baby.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@Entity
public class VideoCategory {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    private Set<Video> videos;

    @Column(unique = true, nullable = false, columnDefinition = "nvarchar(50)")
    private String name;

    public VideoCategory(String name) {
        this.name = name;
    }

    public VideoCategory() {
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

    public Set<Video> getVideos() {
        return videos;
    }

    public void setVideos(Set<Video> videos) {
        this.videos = videos;
    }
}
