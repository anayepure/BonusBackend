package com.utcn.assignment.service;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.model.Votequestion;
import com.utcn.assignment.repository.iVoteQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteQuestionService {
    @Autowired
    iVoteQuestionRepository IVoteQuestionRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    QuestionService questionService;

    public Votequestion saveQuestion(Votequestion voteQuestion){
        return IVoteQuestionRepository.save(voteQuestion);
    }


    public List<Votequestion> getAllVotes()
    {
        return (List<Votequestion>) IVoteQuestionRepository.findAll();
    }

    public boolean samevote(Integer pid, Integer qid)
    {
        boolean same=true;
        List<Votequestion> votes=getAllVotes();
        for (Votequestion v:votes)
        {
            if (v.getVoteQuestionAuthor().getPid()==pid && v.getVoteQuestion().getQid()==qid)
                same=false;

        }
        return same;
    }

    public Votequestion voteQuestion(Integer pid, Integer qid, Integer votetype)
    {
        Author author=authorService.getAuthor(pid);
        Question question=questionService.getQuestion(qid);
        Votequestion voteQuestion=new Votequestion(false,false);
        if (samevote(pid,qid)==true)
        {if (votetype.equals(1))
        {
            voteQuestion.setUpvote(true);
            voteQuestion.setDownvote(false);
            question.upvote();
            author.voteQuestionUp();
        }
        else if (votetype.equals(0))
        {
            voteQuestion.setDownvote(true);
            voteQuestion.setUpvote(false);
            question.downvote();
            author.voteQuestionDown();
        }

        voteQuestion.setVoteQuestion(question);
        voteQuestion.setVoteQuestionAuthor(author);
        this.saveQuestion(voteQuestion);
        return voteQuestion;}
        return null;

    }

}
