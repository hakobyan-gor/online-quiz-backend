package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public enum Status {

    PENDING(0),
    ONLINE(1),
    OFFLINE(2),
    DELETED(3),
    VERIFIED(4);

    @Getter
    @Setter
    private int index;

}
