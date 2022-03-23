package com.utcn.assignment.service;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.repository.iAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthorService {

    @Autowired
    iAuthorRepository IAuthorRepository;
    public List<Author> getAllAuthors()
    {
        return (List<Author>) IAuthorRepository.findAll();
    }

    public Author getAuthor(Integer pid) {
        Optional<Author> author = IAuthorRepository.findById(pid);
        return author.orElse(null);
    }


    public Author findByUsername(String username)
    {
        Author author=IAuthorRepository.findByUsername(username);
        return author;
    }

    public Author saveAuthor(Author author){
        return IAuthorRepository.save(author);
    }

    public Set<Question> getAuthorQuestions(Integer pid)
    {
        Author author=this.getAuthor(pid);
        return author.getQuestions();
    }

}
