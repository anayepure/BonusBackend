package com.utcn.assignment.model;

import javax.persistence.*;

@Entity
@Table( name = "votequestion", uniqueConstraints = { @UniqueConstraint( columnNames = { "pid", "qid" } ) } )
public class Votequestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vqid;
    private Boolean upvote;
    private Boolean downvote;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "pid")
    private Author voteQuestionAuthor;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "qid")
    private Question voteQuestion;

    public Votequestion(Boolean upvote, Boolean downvote) {
        this.upvote = upvote;
        this.downvote = downvote;
    }

    public Votequestion() {

    }

    public Boolean getUpvote() {
        return upvote;
    }

    public void setUpvote(Boolean upvote) {
        this.upvote = upvote;
    }

    public Boolean getDownvote() {
        return downvote;
    }

    public void setDownvote(Boolean downvote) {
        this.downvote = downvote;
    }

    public void setVoteQuestionAuthor(Author voteQuestionAuthor) {
        this.voteQuestionAuthor = voteQuestionAuthor;
    }

    public void setVoteQuestion(Question voteQuestion) {
        this.voteQuestion = voteQuestion;
    }
}
