package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String eMail;
    private String password;

}
