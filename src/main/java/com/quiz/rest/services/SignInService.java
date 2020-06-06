package com.quiz.rest.services;

import com.quiz.models.request.LoginRequest;
import com.quiz.models.response.JwtResponse;
import com.quiz.models.response.ResponseModel;

public interface SignInService {

    ResponseModel<?> signIn(LoginRequest loginRequest);

}
