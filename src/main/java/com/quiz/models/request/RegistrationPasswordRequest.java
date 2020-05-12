package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class RegistrationPasswordRequest {

    private Long id;
    private String password;

}
