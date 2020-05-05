package com.quiz.models;

import lombok.*;

import javax.persistence.*;
import java.util.Random;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "CONFIRMATION_TOKEN")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "CONFIRMATION_TOKEN")
    private int confirmationToken;

    public ConfirmationToken(Long userId) {
        this.userId = userId;
        Random random = new Random();
        confirmationToken = random.nextInt(900000) + 100000;
    }
}
