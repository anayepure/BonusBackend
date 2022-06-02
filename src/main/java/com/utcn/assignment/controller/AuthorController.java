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

import java.util.List;
import java.util.Set;

@Controller
@CrossOrigin(origins="http://localhost:4200")
public class AuthorController {

    @Autowired
    AuthorService authorService;


    @Autowired
    QuestionService questionService;

    @Autowired
    TagService tagService;


    Author author=new Author();


    @RequestMapping(method = RequestMethod.GET, value = "/login")
    @ResponseBody
    public Author logIn(@RequestParam(name = "username") String username,@RequestParam(name = "password") String password) {
        Author author=authorService.findByUsername(username);
        if (author.getPassword().equals(password))
        {
            this.author=author;
            return author;
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save")
    @ResponseBody
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findById")
    @ResponseBody
    public Author getAuthor(@RequestParam(name = "pid") Integer pid) {
        return authorService.getAuthor(pid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getQuestions")
    @ResponseBody
    public Set<Question> getAuthorQuestions(@RequestParam(name = "pid") Integer pid) {
        return authorService.getAuthorQuestions(pid);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAnswers")
    @ResponseBody
    public Set<Answer> getAuthorAnswers(@RequestParam(name = "pid") Integer pid) {
        return authorService.getAuthorAnswers(pid);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteAuthor")
    @ResponseBody
    public String deleteAuthor(@RequestParam(name = "pid") Integer pid) {
        Author author=authorService.getAuthor(pid);
         return authorService.deleteAuthor(pid);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateAuthor")
    @ResponseBody
    public String editAuthor(@RequestBody Author author) {

        Author author1=authorService.getAuthor(author.getPid());
        return authorService.editAuthor(author1.getPid(),author);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/displayAuthors")
    @ResponseBody
    public List<Author> displayAuthors()
    {
        return authorService.getAllAuthors();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/banAuthor")
    @ResponseBody
    public String banAuthor(@RequestParam(name = "pid") Integer pid)
    {
        return authorService.banAuthor(pid);
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/sendAuthor")
    @ResponseBody
    public String sendEmail()
    {
        return authorService.sendMail();
    }*/


}
