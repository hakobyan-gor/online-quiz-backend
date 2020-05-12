package com.quiz.models.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type;
    private String username;

}
