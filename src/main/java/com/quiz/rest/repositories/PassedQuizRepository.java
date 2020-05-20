package com.quiz.rest.repositories;

import com.quiz.models.PassedQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassedQuizRepository extends JpaRepository<PassedQuiz, Long> {

    PassedQuiz findPassedQuizById(Long passedQuizId);

    List<PassedQuiz> findPassedQuizzesByUserId(Long userId);

    List<PassedQuiz> findPassedQuizzesByQuizIdAndUserId(Long quizId, Long userId);

}
