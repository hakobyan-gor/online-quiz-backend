package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationPasswordRequest {

    private Long id;
    private String password;

}
