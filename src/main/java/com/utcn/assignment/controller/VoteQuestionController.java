package com.utcn.assignment.controller;

import com.utcn.assignment.model.Votequestion;
import com.utcn.assignment.service.VoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteQuestionController {
    @Autowired
    VoteQuestionService voteQuestionService;
    //3.1
    @RequestMapping(method = RequestMethod.POST, value = "/voteQuestion")
    @ResponseBody
    public Votequestion voteQuestion(@RequestParam(name = "pid") Integer pid, @RequestParam(name = "qid") Integer qid, @RequestParam(name = "votetype") String votetype)
    {
        return voteQuestionService.voteQuestion(pid,qid,votetype);
    }

}
