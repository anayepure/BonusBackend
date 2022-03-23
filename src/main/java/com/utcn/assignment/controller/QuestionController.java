package com.utcn.assignment.controller;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.service.AuthorService;
import com.utcn.assignment.service.QuestionService;
import com.utcn.assignment.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

@Controller("questionController")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    AuthorService authorService;
    @Autowired
    TagService tagService;

    //1.1
    @RequestMapping(method = RequestMethod.POST, value = "/askQuestion")
    @ResponseBody
    public Question askQuestion(@RequestBody Question question,@RequestParam(name = "pid") Integer pid, @RequestParam(name = "tagtext") String[] tagtext) {
        Author author=authorService.getAuthor(pid);
        question.setAuthor(author);
        question.setQuestioncreationtime(new Date());
        question.setQuestionvotecount(0);
        questionService.setTagsToQuestion(question,tagtext);
        return questionService.saveQuestion(question);

    }


    @RequestMapping(method = RequestMethod.GET, value = "/questionvotes")
    @ResponseBody
    public Integer voteQuestion(@RequestParam(name = "qid") Integer qid) {
        return questionService.getQuestion(qid).getQuestionvotecount();

    }


    //1.2
    @RequestMapping(method = RequestMethod.GET, value = "/displayQuestions")
    @ResponseBody
    public List<Question> displayQuestions()
    {
       return questionService.sortedQuestions();
    }

    //1.3.1
    @RequestMapping(method = RequestMethod.GET, value = "/filterQuestions")
    @ResponseBody
    public List<Question> filterQuestions(@RequestParam(name = "tagtext") String tagtext)
    {
        return questionService.filteredQuestions(tagtext);
    }

    //1.3.2
    @RequestMapping(method = RequestMethod.GET, value = "/filterQuestionsByText")
    @ResponseBody
    public List<Question> filterQuestionsByText(@RequestParam(name = "text") String text)
    {
        return questionService.filteredQuestionsByText(text);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteQuestion")
    @ResponseBody
    public String deleteQuestion(@RequestParam(name = "pid") Integer pid,@RequestParam(name = "qid") Integer qid) {
        Question question=questionService.getQuestion(qid);
        if (question.getAuthor().getPid()==pid) return questionService.deleteQuestion(qid);
        else return "delete failed";

    }

    //2.3
    @RequestMapping(method = RequestMethod.GET, value = "/displayQuestion")
    @ResponseBody
    public Question displayQuestion(@RequestParam(name = "qid") Integer qid) {
        Question question= questionService.getQuestion(qid);
        return question;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/displayQuestionAnswers")
    @ResponseBody
    public Set<Answer> showAnswers(@RequestParam(name = "qid") Integer qid) {
        return questionService.displayAnswers(qid);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/updateQuestion")
    @ResponseBody
    public String editAnswer(@RequestBody Question question, @RequestParam(name = "pid") Integer pid) {

        Question question1=questionService.getQuestion(question.getQid());
        if (question1.getAuthor().getPid()==pid) return questionService.editQuestion(question.getQid(),question);
        else return "edit failed";
    }


}
