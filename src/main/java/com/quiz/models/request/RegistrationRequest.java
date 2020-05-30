package com.quiz.models.request;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RegistrationRequest {

    private String firstName;
    private String lastName;
    private String eMail;
    private String password;

}
