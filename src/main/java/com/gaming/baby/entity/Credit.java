package com.gaming.baby.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Credit {

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "datetime default null")
    private Date dateTime;

    private String creditLog;

    @Column(columnDefinition = "nvarchar(50)")
    private String notes;
}
