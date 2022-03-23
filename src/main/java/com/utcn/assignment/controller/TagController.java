package com.utcn.assignment.controller;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Tag;
import com.utcn.assignment.service.AuthorService;
import com.utcn.assignment.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller("tagController")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/findTagById")
    @ResponseBody
    public Tag getAuthor(@RequestParam(name = "tid") Integer tid) {
        return tagService.getTag(tid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findTagByText")
    @ResponseBody
    public Tag findTagByTagtext(@RequestParam(name = "tagtext") String tagtext) {
        return tagService.addTagtext(tagtext);
    }

}
