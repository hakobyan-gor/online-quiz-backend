package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public enum Status {

    PENDING(1),
    ONLINE(2),
    OFFLINE(3),
    DELETED(4),
    VERIFIED(5);

    @Getter
    @Setter
    private int index;

}
