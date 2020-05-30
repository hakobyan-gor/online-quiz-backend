package com.quiz.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz.models.PassedQuizAnswer;

@Repository
public interface PassedQuizAnswerRepository extends JpaRepository<PassedQuizAnswer, Long> {

}
