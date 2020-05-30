package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationPasswordRequest {

    private Long id;
    private String password;

}
