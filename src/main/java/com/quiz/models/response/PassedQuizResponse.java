package com.quiz.models.response;

import java.util.HashMap;
import java.util.List;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PassedQuizResponse {

    private Long id;
    private List<HashMap<Long, Long>> selectedAndCorrectAnswers;
    private Long score;

}
