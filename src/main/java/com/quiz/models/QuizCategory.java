package com.quiz.models;

import javax.persistence.*;
import java.util.List;
import lombok.*;

@Table(name = "QUIZ_CATEGORY")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
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
