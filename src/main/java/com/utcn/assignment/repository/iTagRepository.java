package com.utcn.assignment.repository;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iTagRepository extends CrudRepository<Tag,Integer> {
    Tag findByTagtext(String tagtext);
}
