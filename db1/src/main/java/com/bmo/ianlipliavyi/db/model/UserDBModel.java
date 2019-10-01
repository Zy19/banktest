package com.bmo.ianlipliavyi.db.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
public class UserDBModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "name")
    private String name;

    @Column(name = "last")
    private String last;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }
}
