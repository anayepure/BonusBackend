package com.utcn.assignment.model;

import javax.persistence.*;

@Entity
@Table( name = "voteanswer", uniqueConstraints = { @UniqueConstraint( columnNames = { "pid", "aid" } ) } )
public class Voteanswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vaid;
    private Boolean upvote;
    private Boolean downvote;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "pid")
    private Author voteAnswerAuthor;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "aid")
    private Answer voteAnswer;

    public Voteanswer(Boolean upvote, Boolean downvote) {
        this.upvote = upvote;
        this.downvote = downvote;

    }

    public Voteanswer() {

    }

    public Integer getVaid() {
        return vaid;
    }

    public void setVaid(Integer vaid) {
        this.vaid = vaid;
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

    public Author getVoteAnswerAuthor() {
        return voteAnswerAuthor;
    }

    public void setVoteAnswerAuthor(Author voteAnswerAuthor) {
        this.voteAnswerAuthor = voteAnswerAuthor;
    }

    public Answer getVoteAnswer() {
        return voteAnswer;
    }

    public void setVoteAnswer(Answer voteAnswer) {
        this.voteAnswer = voteAnswer;
    }


}
