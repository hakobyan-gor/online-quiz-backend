package com.quiz.rest.services;

import com.quiz.models.QuizCategory;

import java.util.List;

public interface QuizCategoryService {

    public List<QuizCategory> getRootCategories();

    public List<QuizCategory> getCategoriesByRootId(Long id);

}
