package com.quiz.models.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JwtResponse {

    private String token;
    private String type;
    private String username;

}
