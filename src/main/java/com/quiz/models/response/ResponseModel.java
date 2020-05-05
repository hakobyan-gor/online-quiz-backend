package com.quiz.models.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseModel<T> {

    private boolean success;
    private String message;
    private T data;
    private HttpStatus httpStatus;

}