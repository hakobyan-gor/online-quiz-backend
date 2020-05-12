package com.quiz.models;

import javax.persistence.*;
import java.util.Random;
import lombok.*;

@Table(name = "CONFIRMATION_TOKEN")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
@Entity
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
