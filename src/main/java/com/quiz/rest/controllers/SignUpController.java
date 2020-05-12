package com.quiz.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.models.request.RegistrationRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.quiz.models.response.JwtResponse;
import com.quiz.rest.services.SignUpService;
import org.springframework.http.HttpStatus;
import com.quiz.models.*;
import java.util.HashMap;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    @PostMapping
    public ResponseEntity registration(@RequestBody RegistrationRequest registrationRequest) {

        User user = signUpService.signUp(registrationRequest);

        HashMap<String, String> message = new HashMap<>();

        if (user == null) {
            message.put("Error", "This email already exists!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @PostMapping("/verify-eMail")
    public ResponseEntity verifyEmail(@RequestBody ConfirmationToken confirmationToken) {

        HashMap<String, Object> message = new HashMap<>();
        JwtResponse jwtResponse = signUpService.verifyEMail(confirmationToken);

        if (jwtResponse == null){
            message.put("Error", "Confirmation Token is incorrect!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }

        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity refreshToken() {
        return null;
    }

}
