package com.utcn.assignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Answer implements Serializable,Comparable<Answer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer aid;
    @Column(name="answertext")
    private String answertext;
    @Column(name="answercreationtime")
    private Date answercreationtime;
    @Column(name="answervotecount")
    private Integer answervotecount=0;
    //@JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerpid")
    private Author answerAuthor;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerqid")
    private Question answerQuestion;

    @JsonIgnore
    @OneToMany(mappedBy="voteAnswer",cascade = CascadeType.ALL)
    private Set<Voteanswer> votesAnswer=new HashSet<Voteanswer>();



    public Answer(String answertext, Date answercreationtime) {
        this.answertext = answertext;
        this.answercreationtime = answercreationtime;
    }



    public Answer() {

    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAnswertext() {
        return answertext;
    }

    public void setAnswertext(String answertext) {
        this.answertext = answertext;
    }

    public Date getAnswercreationtime() {
        return answercreationtime;
    }

    public void setAnswercreationtime(Date answercreationTime) {
        this.answercreationtime = answercreationTime;
    }

    public Author getAnswerAuthor() {
        return answerAuthor;
    }

    public void setAnswerAuthor(Author answerAuthor) {
        this.answerAuthor = answerAuthor;
    }

    public Question getAnswerQuestion() {
        return answerQuestion;
    }

    public void setAnswerQuestion(Question answerQuestion) {
        this.answerQuestion = answerQuestion;
    }

    public Integer getAnswervotecount() {
        return answervotecount;
    }

    public void setAnswervotecount(Integer answervotecount) {
        this.answervotecount = answervotecount;
    }

    public void upvote()
    {
        this.answervotecount++;
    }

    public void downvote()
    {
        this.answervotecount--;
    }

    @Override
    public int compareTo(Answer o) {
        return 0;
    }

    public static class Comparators {
        public static final Comparator<Answer> votes = (Answer o1, Answer o2) -> Integer.compare(o1.answervotecount, o2.answervotecount);

    }

    public Set<Voteanswer> getVotesAnswer() {
        return votesAnswer;
    }
}
