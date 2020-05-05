package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String eMail;
    private String password;

}
