package com.quiz.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
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
