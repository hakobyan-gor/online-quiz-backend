package com.quiz.rest.services;

import com.quiz.models.ConfirmationToken;
import com.quiz.models.User;
import com.quiz.models.request.RegistrationRequest;
import com.quiz.models.response.JwtResponse;

public interface SignUpService {

    public User signUp(RegistrationRequest registrationRequest);

    JwtResponse verifyEMail(ConfirmationToken confirmationToken);
}
