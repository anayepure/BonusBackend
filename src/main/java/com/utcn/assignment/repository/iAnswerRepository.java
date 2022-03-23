package com.utcn.assignment.repository;

import com.utcn.assignment.model.Answer;
import com.utcn.assignment.model.Author;
import com.utcn.assignment.model.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface iAnswerRepository extends CrudRepository<Answer,Integer> {
    Answer findByAid(Integer Aid);
    @Query(
            value = "SELECT * FROM answer a WHERE a.answerqid = :answerqid ORDER BY a.answervotecount DESC",
            nativeQuery = true)
    List<Answer> findByAnswerqidOrderByAnswervotecount1(@Param("answerqid") Integer qid);

    /*@Transactional
    @Modifying
    @Query(
            value = "DELETE FROM answer a WHERE a.aid = :aid ",
            nativeQuery = true)
   Answer delete(@Param("aid") Integer aid);*/
}
