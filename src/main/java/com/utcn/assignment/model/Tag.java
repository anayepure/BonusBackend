package com.utcn.assignment.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tid;
    @Column(name="tagtext")
    private String tagtext;
    @ManyToMany(mappedBy = "tags")
    private Set<Question> questions;

    public Tag(String tagtext) {
        this.tagtext = tagtext;
    }

    public Tag() {

    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTagtext() {
        return tagtext;
    }

    public void setTagtext(String tagtext) {
        this.tagtext = tagtext;
    }
}
