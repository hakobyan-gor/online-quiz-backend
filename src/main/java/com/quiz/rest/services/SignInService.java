package com.quiz.rest.services;

import com.quiz.models.request.LoginRequest;

import java.util.Map;

public interface SignInService {

    public Map<String, Object> signIn(LoginRequest loginRequest);

}
