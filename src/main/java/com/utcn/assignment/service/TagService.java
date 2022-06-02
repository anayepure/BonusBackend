package com.utcn.assignment.service;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.model.Tag;
import com.utcn.assignment.repository.iAuthorRepository;
import com.utcn.assignment.repository.iQuestionRepository;
import com.utcn.assignment.repository.iTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TagService {

    @Autowired
    iTagRepository ITagRepository;
    public List<Tag> getAllTags()
    {
        return (List<Tag>) ITagRepository.findAll();
    }
    public Tag saveTag(Tag tag){
        return ITagRepository.save(tag);
    }
    public Tag getTag(Integer tid) {
        Optional<Tag> tag = ITagRepository.findById(tid);
        return tag.orElse(null);
    }



    public Tag findByTagtext(String tagtext)
    {
        Tag tag=ITagRepository.findByTagtext(tagtext);
        return tag;
    }

    public Tag addTagtext(String tagtext)
    {
        Tag tag=ITagRepository.findByTagtext(tagtext);
        if (tag!=null) return tag;
        else
        {   Tag tag1=new Tag(tagtext);
            this.saveTag(tag1);
            return tag1;}
    }
}
