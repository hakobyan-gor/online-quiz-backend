package com.quiz.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz.models.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findQuestionById(Long id);

    List<Question> findQuestionByQuizId(Long id);

    boolean existsQuestionById(Long id);

    boolean existsQuestionsByQuizId(Long quizId);

}
