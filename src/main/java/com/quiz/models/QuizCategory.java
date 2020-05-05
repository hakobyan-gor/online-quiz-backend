package com.quiz.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "QUIZ_CATEGORY")
public class QuizCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DESCRIPTION", length = 1500)
    private String description;

    @Column(name = "ROOT_CATEGORY_ID")
    private Long rootCategoryId;

    @OneToMany(mappedBy = "theQuizCategory")
    private List<Quiz> quiz;

}
