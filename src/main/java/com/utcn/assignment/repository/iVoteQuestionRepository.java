package com.utcn.assignment.repository;

import com.utcn.assignment.model.Question;
import com.utcn.assignment.model.Votequestion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iVoteQuestionRepository extends CrudRepository<Votequestion,Integer> {
    //List<Votequestion> findAllByOrderByQuestioncreationtimeDesc();
}
