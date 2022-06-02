package com.utcn.assignment.service;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Voteanswer;
import com.utcn.assignment.model.Votequestion;
import com.utcn.assignment.repository.iAuthorRepository;
import com.utcn.assignment.repository.iVoteAnswerRepository;
import com.utcn.assignment.repository.iVoteQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteAnswerService {
    @Autowired
    iVoteAnswerRepository IVoteAnswerRepository;

    @Autowired
    AuthorService authorService;

    @Autowired
    AnswerService answerService;

    @Autowired
    iAuthorRepository IAuthorRepository;




    public Voteanswer saveAnswerVote(Voteanswer voteAnswer){
        return IVoteAnswerRepository.save(voteAnswer);
    }

    public List<Voteanswer> getAllVotes()
    {
        return (List<Voteanswer>) IVoteAnswerRepository.findAll();
    }

    public boolean samevote(Integer pid, Integer aid)
    {
        boolean same=true;
        List<Voteanswer> votes=getAllVotes();
        for (Voteanswer v:votes)
        {
            if (v.getVoteAnswerAuthor().getPid()==pid && v.getVoteAnswer().getAid()==aid)
                same=false;

        }
        return same;
    }

    public Voteanswer voteAnswer(Integer pid, Integer aid, String votetype)
    {
        Author author=authorService.getAuthor(pid);
        Answer answer=answerService.getAnswer(aid);
        Voteanswer voteanswer=new Voteanswer(false, false);
        if (samevote(pid, aid)==true)
        {if (votetype.equals("upvote"))
        {
            voteanswer.setUpvote(true);
            answer.upvote();
            author.voteAnswerUp();
        }

        else if (votetype.equals("downvote"))
        {
            voteanswer.setDownvote(true);
            answer.downvote();
            author.voteAnswerDown();
        }

        voteanswer.setVoteAnswerAuthor(author);
        voteanswer.setVoteAnswer(answer);
        this.saveAnswerVote(voteanswer);
        return voteanswer;}

        return null;

    }

    public String voteAnswerlose(Integer pid)
    {
        try {
            Author initialAuthor = authorService.getAuthor(pid);
            initialAuthor.voteAnotherAnswerDown();
            IAuthorRepository.save(initialAuthor);
            return "Edit success.";

        }
        catch (Exception e){
            return "Edit failed.";
        }

    }





}
