package com.quiz.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz.models.QuizCategory;

import java.util.List;

@Repository
public interface QuizCategoryRepository extends JpaRepository<QuizCategory, Long> {

    List<QuizCategory> findByRootCategoryId(Long id);

    QuizCategory findQuizCategoryById(Long id);

}
