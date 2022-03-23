package com.utcn.assignment.repository;

import com.utcn.assignment.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface iAuthorRepository extends CrudRepository<Author,Integer> {
    Author findByUsername(String username);



}
