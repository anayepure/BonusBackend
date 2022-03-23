package com.utcn.assignment.service;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.repository.iAnswerRepository;
import com.utcn.assignment.repository.iAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    iAnswerRepository IAnswerRepository;
    @Autowired
    AuthorService authorService;

    public Answer saveAnswer(Answer answer){
        return IAnswerRepository.save(answer);
    }

    public Answer getAnswer(Integer aid) {
        Optional<Answer> answer = IAnswerRepository.findById(aid);
        return answer.orElse(null);
    }


    public String deleteAnswer(Integer aid) {
        try {
            IAnswerRepository.delete(this.getAnswer(aid));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public String editAnswer(Integer aid, Answer answer)
    {
        try {
            Answer initialAnswer = this.getAnswer(aid);
            initialAnswer.setAnswertext(answer.getAnswertext());
            IAnswerRepository.save(initialAnswer);
            return "Edit success.";

        }
    catch (Exception e){
        return "Edit failed.";
    }

    }

    public boolean containsAnswer(Answer answer, Integer pid)
    {
        Author author=authorService.getAuthor(pid);
        ArrayList<Answer> answers=new ArrayList<>(author.getAnswers());
        Boolean contains=false;
        for (int i=0;i<answers.size();i++)
        {
            if (answers.get(i).getAid()==answer.getAid()) contains=true;
        }

        return contains;
    }

    public List<Answer> answersSorted(Integer qid)
    {
        return IAnswerRepository.findByAnswerqidOrderByAnswervotecount1(qid);

    }










}
