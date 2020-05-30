package com.quiz.models;

import javax.persistence.*;
import lombok.*;

@Table(name = "ROLE")
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ROLE")
    private String role;

}
