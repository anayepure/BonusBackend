package com.utcn.assignment.service;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.repository.iAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSenderImpl;*/

@Service
public class AuthorService {

    @Autowired
    iAuthorRepository IAuthorRepository;

   /* @Autowired
    private JavaMailSender mailSender;*/

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

    public Set<Answer> getAuthorAnswers(Integer pid)
    {
        Author author=this.getAuthor(pid);
        return author.getAnswers();
    }

    public String deleteAuthor(Integer aid) {
        try {
            IAuthorRepository.delete(this.getAuthor(aid));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public String editAuthor(Integer aid, Author author)
    {
        try {
            Author initialAuthor = this.getAuthor(aid);
            initialAuthor.setUsername(author.getUsername());
            initialAuthor.setPassword(author.getPassword());
            IAuthorRepository.save(initialAuthor);
            return "Edit success.";

        }
        catch (Exception e){
            return "Edit failed.";
        }

    }

    public String banAuthor(Integer pid)
    {

        try {
            Author initialAuthor = this.getAuthor(pid);
            initialAuthor.setIsbanned(1);
            IAuthorRepository.save(initialAuthor);
            return "Edit success.";

        }
        catch (Exception e){
            return "Edit failed.";
        }

    }

    /*public void sendMail(String toEmail, String subject, String body)
    {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("mirelavaida2509@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);

        System.out.println("Mail Sent Successfuly");
    }

    @EventListener(ApplicationReadyEvent.class)
    public String sendMail()
    {
        sendMail("Iepure.Ana.Andreea@gmail.com",
                "Banned User",
                "You have been banned from our website due to innapropriate language");
        return "email sent successfully";
    }*/




}
