package com.utcn.assignment.controller;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Tag;
import com.utcn.assignment.service.AuthorService;
import com.utcn.assignment.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("tagController")
@CrossOrigin(origins="http://localhost:4200")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/displayTags")
    @ResponseBody
    public List<Tag> displayTags()
    {
        return tagService.getAllTags();
    }

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
