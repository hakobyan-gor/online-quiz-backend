package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class LoginRequest {

    private String username;
    private String password;

}
