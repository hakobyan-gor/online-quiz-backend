package com.quiz.models;

import javax.persistence.*;
import lombok.*;

@Table(name = "AUTHENTICATION_TOKEN")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "USER_ID")
    private Long userId;

}
