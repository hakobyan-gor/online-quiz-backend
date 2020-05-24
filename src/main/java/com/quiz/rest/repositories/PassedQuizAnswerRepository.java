package com.quiz.rest.repositories;

import com.quiz.models.PassedQuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassedQuizAnswerRepository extends JpaRepository<PassedQuizAnswer, Long> {

}
