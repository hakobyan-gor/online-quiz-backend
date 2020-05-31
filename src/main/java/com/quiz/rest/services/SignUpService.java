package com.quiz.rest.services;

import com.quiz.models.request.RegistrationRequest;
import com.quiz.models.response.JwtResponse;

import javax.mail.MessagingException;

import com.quiz.models.ConfirmationToken;
import com.quiz.models.User;

public interface SignUpService {

    User signUp(RegistrationRequest registrationRequest) throws MessagingException;

    JwtResponse verifyEMail(ConfirmationToken confirmationToken);
}
