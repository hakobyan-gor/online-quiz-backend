package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public enum  AnswerStatus {

    TRUE(1),
    FALSE(2);

    @Getter
    @Setter
    private int index;

}