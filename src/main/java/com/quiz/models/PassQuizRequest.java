package com.quiz.models;

import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PassQuizRequest {

    private Map<Long, List<Long>> questionsIDsAndSelectedAnswers;
    private Long userId;

}
