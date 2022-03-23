package com.utcn.assignment.repository;

import com.utcn.assignment.model.Voteanswer;
import com.utcn.assignment.model.Votequestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iVoteAnswerRepository extends CrudRepository<Voteanswer,Integer> {
   // List<Voteanswer> findAllByOrderByAnswercreationtimeDesc();
}
