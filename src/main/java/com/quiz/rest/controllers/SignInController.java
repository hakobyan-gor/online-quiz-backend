package com.quiz.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quiz.models.response.ResponseModel;
import com.quiz.models.request.LoginRequest;
import com.quiz.rest.services.SignInService;

@RestController
@RequestMapping("/sign-in")
public class SignInController {

    private final SignInService signInService;

    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping
    public ResponseModel<?> login(@RequestBody LoginRequest loginRequest) {

        return signInService.signIn(loginRequest);

    }

}
