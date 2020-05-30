package com.quiz.models.response;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type;
    private String username;

}
