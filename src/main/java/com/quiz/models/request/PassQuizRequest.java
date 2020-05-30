package com.quiz.models.request;

import java.util.List;
import java.util.Map;
import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class PassQuizRequest {

    private Map<Long, List<Long>> questionsIDsAndSelectedAnswers;
    private Long quizId;
    private Long userId;

}
