package com.utcn.assignment.repository;

import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import com.utcn.assignment.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface iQuestionRepository extends CrudRepository<Question,Integer> {
    List<Question> findAllByOrderByQuestioncreationtimeDesc();
    Question findByQid(Integer Qid);


}
