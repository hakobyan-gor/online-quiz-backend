package com.quiz.rest.services.impl;

import com.quiz.models.QuizCategory;
import com.quiz.rest.repositories.QuizCategoryRepository;
import com.quiz.rest.services.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {

    private QuizCategoryRepository quizCategoryRepository;

    @Autowired
    public QuizCategoryServiceImpl(QuizCategoryRepository quizCategoryRepository) {
        this.quizCategoryRepository = quizCategoryRepository;
    }

    @Override
    public List<QuizCategory> getRootCategories() {
        return quizCategoryRepository.findByRootCategoryId(null);
    }

    @Override
    public List<QuizCategory> getCategoriesByRootId(Long id) {
        return quizCategoryRepository.findByRootCategoryId(id);
    }

}
