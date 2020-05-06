package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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