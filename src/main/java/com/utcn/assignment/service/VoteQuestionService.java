package com.utcn.assignment.service;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.model.Votequestion;
import com.utcn.assignment.repository.iVoteQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Votequestion voteQuestion(Integer pid, Integer qid, String votetype)
    {
        Author author=authorService.getAuthor(pid);
        Question question=questionService.getQuestion(qid);
        Votequestion voteQuestion=new Votequestion(false,false);
        if (votetype.equals("upvote"))
        {
            voteQuestion.setUpvote(true);
            voteQuestion.setDownvote(false);
            question.upvote();
        }
        else if (votetype.equals("downvote"))
        {
            voteQuestion.setDownvote(true);
            voteQuestion.setUpvote(false);
            question.downvote();
        }

        voteQuestion.setVoteQuestion(question);
        voteQuestion.setVoteQuestionAuthor(author);
        this.saveQuestion(voteQuestion);
        return voteQuestion;

    }

}
