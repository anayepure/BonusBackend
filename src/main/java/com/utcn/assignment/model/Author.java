package com.utcn.assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "answers","questions"})

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@EqualsAndHashCode.Include
    private Integer pid;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="score")
    private Integer score;
    @Column(name="isadmin")
    private Integer isadmin;
    @Column(name="isbanned")
    private Integer isbanned;




    @OneToMany(mappedBy="author",cascade = CascadeType.ALL)
    private Set<Question> questions=new HashSet<Question>();


    @OneToMany(mappedBy="answerAuthor",cascade = CascadeType.ALL)
    private Set<Answer> answers=new HashSet<Answer>();


    @OneToMany(mappedBy="voteQuestionAuthor",cascade = CascadeType.ALL)
    private Set<Votequestion> votesQuestion=new HashSet<Votequestion>();


    @OneToMany(mappedBy="voteAnswerAuthor",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Voteanswer> votesAnswer=new HashSet<Voteanswer>();


    public Author(String username, String password) {
        this.username = username;
        this.password = password;
        this.score=0;
        this.isadmin=0;
        this.isbanned=0;
    }


    public Author() {

    }

    public void voteQuestionUp()
    {
        this.score+=5;
    }

    public void voteAnswerUp()
    {
        this.score+=10;
    }

    public void voteQuestionDown()
    {
        this.score-=2;
    }

    public void voteAnswerDown()
    {
        this.score-=2;
    }

    public void voteAnotherAnswerDown()
    {
        this.score-=1;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public Set<Question> getQuestions() {
        return this.questions;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Integer isadmin) {
        this.isadmin = isadmin;
    }

    public Integer getIsbanned() {
        return isbanned;
    }

    public void setIsbanned(Integer isbanned) {
        this.isbanned = isbanned;
    }
}
