package com.quiz.models;

import lombok.*;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PassedQuizResponse {

    private Long id;
    private List<HashMap<Long, Long>> selectedAndCorrectAnswers;
    private Long score;

}
