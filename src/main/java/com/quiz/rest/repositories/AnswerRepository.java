package com.quiz.rest.repositories;

import com.quiz.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findAnswerById(Long id);

    List<Answer> findAnswerByQuestionId(Long id);

    boolean existsAnswerById(Long id);

    boolean existsAnswersByQuestionId(Long id);

}
