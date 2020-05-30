package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class LoginRequest {

    private String username;
    private String password;

}
