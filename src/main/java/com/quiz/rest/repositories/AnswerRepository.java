package com.quiz.rest.repositories;

import com.quiz.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer findAnswerById(Long id);

    List<Answer> findAnswerByQuestionId(Long id);

    boolean existsAnswerById(Long id);

    boolean existsAnswersByQuestionId(Long id);

    @Query("SELECT id FROM Answer WHERE questionId = :questionId AND answerStatus = 0")
    List<Long> findCorrectAnswerIDByQuestionId(Long questionId);

    @Query(value = "SELECT * FROM ANSWER WHERE QUESTION_ID = :questionId AND STATUS = 0", nativeQuery = true)
    List<Answer> findCorrectAnswersByQuestionId(Long questionId);

}
