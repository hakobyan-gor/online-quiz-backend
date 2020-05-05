package com.quiz.rest.repositories;

import com.quiz.models.QuizCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizCategoryRepository extends JpaRepository<QuizCategory, Long> {

    List<QuizCategory> findByRootCategoryId(Long id);

}
