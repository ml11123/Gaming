package com.gaming.baby.entity;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Date;

@Entity
public class News {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String slug;

    private Date datetime;

    private String image;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    private String title;

    @Column(columnDefinition = "ntext")
    private String content;

    private boolean display;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public News(){}

    public News(String title, String content) {
        this.title = title;
        this.content = content;
        this.datetime = new Date();
        this.display = true;
        this.slug = this.title.replaceAll("\\s", "-");

    }
}
