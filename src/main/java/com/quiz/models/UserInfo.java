package com.quiz.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.enums.UserGender;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "USER_INFO")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "GENDER")
    @Enumerated(EnumType.ORDINAL)
    private UserGender userGender;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    @JsonIgnore
    private User theUser;


}
