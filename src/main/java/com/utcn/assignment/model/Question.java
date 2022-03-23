package com.utcn.assignment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler", "questionAnswers"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Question implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer qid;
    @Column(name="title")
    private String title;
    @Column(name="questiontext")
    private String questionText;
    @Column(name="questioncreationtime")
    private Date questioncreationtime;
    @Column(name="questionvotecount")
    private Integer questionvotecount=0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Author author;


    @ManyToMany
    @JoinTable(name = "tagquestion",
            joinColumns =
                    { @JoinColumn(name = "qid", referencedColumnName = "qid") },
            inverseJoinColumns =
                    { @JoinColumn(name = "tid", referencedColumnName = "tid") })

    private Set<Tag> tags=new HashSet<Tag>();

    @OneToMany(mappedBy="voteQuestion",cascade = CascadeType.ALL)
    private Set<Votequestion> votesQuestion=new HashSet<Votequestion>();

    @JsonIgnore
    @OneToMany(mappedBy="answerQuestion",cascade = CascadeType.ALL)
    @OrderBy("answervotecount DESC")
    private Set<Answer> questionAnswers=new HashSet<Answer>();


    public Question(String title, String questionText, Author author) {
        this.title = title;
        this.questionText = questionText;
        this.questioncreationtime = new Date();
        this.author = author;
        //this.tags=new HashSet<Tag>();
        //this.questionAnswers=new HashSet<>();
        this.questionvotecount=0;
    }

    public Question() {

    }

    public Integer getQid() {
        return qid;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestionText() {
        return questionText;
    }

    public Date getQuestioncreationtime() {
        return questioncreationtime;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setQuestioncreationtime(Date questioncreationtime) {
        this.questioncreationtime = questioncreationtime;
    }



    public void addTags(Tag tag)
    {
        tags.add(tag);

    }



    public Set<Tag> getTags() {
        return tags;
    }

    @JsonIgnore
    public Set<Answer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void upvote()
    {
        this.questionvotecount++;
    }

    public void downvote()
    {
        this.questionvotecount--;
    }


    public void setQuestionvotecount(Integer questionvotecount) {
        this.questionvotecount = questionvotecount;
    }

    public Integer getQuestionvotecount() {
        return questionvotecount;
    }

    public void setQuestionAnswers(Set<Answer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public Set<Votequestion> getVotesQuestion() {
        return votesQuestion;
    }

    public Set<Answer> getAnswers() {
        return questionAnswers;
    }

    public void sortAnswers()
    {
       /* List<Answer> sortedList = getAnswers().stream()
                .sorted(Comparator.comparingInt(questionAnswers::forEach.))
                .collect(Collectors.toList());*/

        Set<Answer> newans
                = new TreeSet<Answer>();
        this.setQuestionAnswers(newans);

    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void setVotesQuestion(Set<Votequestion> votesQuestion) {
        this.votesQuestion = votesQuestion;
    }
}
