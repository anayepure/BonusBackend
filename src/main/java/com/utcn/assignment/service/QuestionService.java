package com.utcn.assignment.service;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.model.Tag;
import com.utcn.assignment.repository.iAnswerRepository;
import com.utcn.assignment.repository.iQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {
    @Autowired
    iQuestionRepository IQuestionRepository;
    @Autowired
    iAnswerRepository IAnswerRepository;
    @Autowired
    TagService tagService;
    @Autowired
    AuthorService authorService;

    public List<Question> getAllQuestions()
    {
        return (List<Question>) IQuestionRepository.findAll();
    }

    public Question saveQuestion(Question question){


        return IQuestionRepository.save(question);
    }

    public void setTagsToQuestion(Question question,String[] tagtext)
    {

        for (int i=0;i<tagtext.length;i++)
        {
            Tag tag = tagService.addTagtext(tagtext[i]);
            question.addTags(tag);}
    }


    public List<Question> sortedQuestions()
    {
        List<Question> questions= IQuestionRepository.findAllByOrderByQuestioncreationtimeDesc();

        return questions;
    }

    public List<Question> filteredQuestions(String tagtext)
    {
        Tag tag=tagService.findByTagtext(tagtext);
        ArrayList<Question> questions=new ArrayList<>(getAllQuestions());
        List<Question> filteredQuestions=new ArrayList<Question>();
        for (int i=0;i<questions.size();i++)
        {
            Set<Tag> tags=questions.get(i).getTags();
            if (tags.contains(tag)) filteredQuestions.add(questions.get(i));
        }
        return filteredQuestions;
    }

    public List<Question> filteredQuestionsByText(String text)
    {
        ArrayList<Question> questions=new ArrayList<>(getAllQuestions());
        List<Question> filteredQuestions=new ArrayList<Question>();
        for (int i=0;i<questions.size();i++)
        {
            if (questions.get(i).getTitle().toLowerCase().indexOf(text.toLowerCase())!=-1) filteredQuestions.add(questions.get(i));
        }
        return filteredQuestions;
    }

    public Question getQuestion(Integer qid) {
        Optional<Question> question = IQuestionRepository.findById(qid);
        return question.orElse(null);
    }

   /* public Question getSortedQuestion(Integer qid)
    {
        Question question = this.getQuestion(qid);
        List<Answer> answer=new ArrayList<Answer>(question.getQuestionAnswers());
        Arrays.sort(answer, Comparator.<Answer, Integer>comparing(Answer::getAnswervotecount));

    }*/

    public String deleteQuestion(Integer qid) {
        try {
            IQuestionRepository.delete(this.getQuestion(qid));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

   public Set<Answer> displayAnswers(Integer qid)
   {
       Question question=this.getQuestion(qid);

       return question.getQuestionAnswers();
   }


    public String editQuestion(Integer qid, Question question)
    {
        try {
            Question initialQuestion = this.getQuestion(qid);
            initialQuestion.setQuestionText(question.getQuestionText());
            IQuestionRepository.save(initialQuestion);
            return "Edit success.";

        }
        catch (Exception e){
            return "Edit failed.";
        }

    }




}
