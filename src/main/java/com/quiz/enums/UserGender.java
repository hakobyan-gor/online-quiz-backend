package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public enum UserGender {

    MALE(1),
    FEMALE(2),
    OTHER(3),
    UNKNOWN(4);

    @Getter
    @Setter
    private Integer index;

}
