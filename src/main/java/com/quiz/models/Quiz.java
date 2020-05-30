package com.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.List;
import lombok.*;

@Table(name = "QUIZ")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CATEGORY_ID")
    private Long categoryId;

    @OneToMany(mappedBy = "theQuiz")
    private List<Question> questionList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private QuizCategory theQuizCategory;

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", questionList=" + questionList +
                '}';
    }
}
