package com.quiz.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Email {
	
	private Map<String, Object> props;
	private String subject;
	private String email;

}
