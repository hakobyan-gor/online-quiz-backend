package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public enum  AnswerStatus {

    TRUE(0),
    FALSE(1);

    @Getter
    @Setter
    private int index;

}