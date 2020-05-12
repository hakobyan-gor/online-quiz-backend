package com.quiz.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.quiz.models.request.LoginRequest;
import com.quiz.rest.services.SignInService;
import org.springframework.http.HttpStatus;
import java.util.Map;

@RestController
@RequestMapping("/sign-in")
public class SignInController {

    private final SignInService signInService;

    @Autowired
    public SignInController(SignInService signInService) {
        this.signInService = signInService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {

        Map message = signInService.signIn(loginRequest);

        if (message.get("error") != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.get("error"));

        if (message.get("not found") != null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message.get("not found"));

        return ResponseEntity.status(HttpStatus.OK).body(message.get("success"));

    }

}
