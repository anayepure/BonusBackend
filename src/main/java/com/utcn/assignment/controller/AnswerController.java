package com.utcn.assignment.controller;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.service.AnswerService;
import com.utcn.assignment.service.AuthorService;
import com.utcn.assignment.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class AnswerController {
    @Autowired
    AnswerService answerService;

    @Autowired
    AuthorService authorService;

    @Autowired
    QuestionService questionService;


    //2.1
    @RequestMapping(method = RequestMethod.POST, value = "/respond")
    @ResponseBody
    public Answer respond(@RequestBody Answer answer, @RequestParam(name = "pid") Integer pid,@RequestParam(name = "qid") Integer qid) {
        Author author=authorService.getAuthor(pid);
        Question question=questionService.getQuestion(qid);
        answer.setAnswercreationtime(new Date());
        answer.setAnswerAuthor(author);
        answer.setAnswerQuestion(question);
        return answerService.saveAnswer(answer);

    }

    //2.2.1
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    @ResponseBody
    public String delete(@RequestParam(name = "pid") Integer pid, @RequestParam(name = "aid") Integer aid)
    {
        Answer answer=answerService.getAnswer(aid);
        if (answer.getAnswerAuthor().getPid()==pid) return answerService.deleteAnswer(aid);
        else return "delete failed";


    }

    //2.2.2
    @RequestMapping(method = RequestMethod.GET, value = "/update")
    @ResponseBody
    public String editAnswer(@RequestBody Answer answer, @RequestParam(name = "pid") Integer pid) {

        Answer answer1=answerService.getAnswer(answer.getAid());
        if (answer1.getAnswerAuthor().getPid()==pid) return answerService.editAnswer(answer.getAid(),answer);
        else return "edit failed";
    }

    //3.3
    @RequestMapping(method = RequestMethod.GET, value = "/sortedAnswers")
    @ResponseBody
    public List<Answer> sortedAnswers(@RequestParam(name = "qid") Integer qid) {
       return answerService.answersSorted(qid);
    }




}
