package com.quiz.rest.repositories;

import com.quiz.models.Quiz;
import com.quiz.models.response.ResponseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  QuizRepository extends JpaRepository<Quiz, Long> {

    Quiz findQuizById(Long id);

    boolean existsById(Long id);

    List<Quiz> findQuizByCategoryId(Long id);

    //List<Quiz> findAllQuizzes();

    //Quiz updateQuiz(Quiz quiz);

}
