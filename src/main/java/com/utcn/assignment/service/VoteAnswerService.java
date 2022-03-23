package com.utcn.assignment.service;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Voteanswer;
import com.utcn.assignment.model.Votequestion;
import com.utcn.assignment.repository.iVoteAnswerRepository;
import com.utcn.assignment.repository.iVoteQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteAnswerService {
    @Autowired
    iVoteAnswerRepository IVoteAnswerRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    AnswerService answerService;

    public Voteanswer saveAnswerVote(Voteanswer voteAnswer){
        return IVoteAnswerRepository.save(voteAnswer);
    }

    public Voteanswer voteAnswer(Integer pid, Integer aid, String votetype)
    {
        Author author=authorService.getAuthor(pid);
        Answer answer=answerService.getAnswer(aid);
        Voteanswer voteanswer=new Voteanswer(false, false);

        if (votetype.equals("upvote"))
        {
            voteanswer.setUpvote(true);
            answer.upvote();
        }

        else if (votetype.equals("downvote"))
        {
            voteanswer.setDownvote(true);
            answer.downvote();
        }

        voteanswer.setVoteAnswerAuthor(author);
        voteanswer.setVoteAnswer(answer);
        this.saveAnswerVote(voteanswer);
        return voteanswer;

    }



}
