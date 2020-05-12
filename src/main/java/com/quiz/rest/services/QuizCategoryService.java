package com.quiz.rest.services;

import com.quiz.models.QuizCategory;
import java.util.List;

public interface QuizCategoryService {

    List<QuizCategory> getRootCategories();

    List<QuizCategory> getCategoriesByRootId(Long id);

}
