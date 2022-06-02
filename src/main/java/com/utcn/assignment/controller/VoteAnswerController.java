package com.utcn.assignment.controller;

import com.utcn.assignment.model.Voteanswer;
import com.utcn.assignment.model.Votequestion;
import com.utcn.assignment.service.VoteAnswerService;
import com.utcn.assignment.service.VoteQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class VoteAnswerController {

    @Autowired
    VoteAnswerService voteAnswerService;

    @RequestMapping(method = RequestMethod.POST, value = "/voteAnswer")
    @ResponseBody
    public Voteanswer voteAnswer(@RequestParam(name = "pid") Integer pid, @RequestParam(name = "aid") Integer aid, @RequestParam(name = "votetype") String votetype)
    {
        return voteAnswerService.voteAnswer(pid,aid,votetype);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/votedownAnswer")
    @ResponseBody
    public String votedownAnswer(@RequestParam(name = "pid") Integer pid)
    {
        return voteAnswerService.voteAnswerlose(pid);
    }



}
