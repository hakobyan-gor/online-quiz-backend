package com.quiz.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.models.request.RegistrationRequest;
import org.springframework.web.bind.annotation.*;
import com.quiz.models.response.ResponseModel;
import com.quiz.models.response.JwtResponse;
import com.quiz.rest.services.SignUpService;
import org.springframework.http.HttpStatus;
import com.quiz.models.*;

@RestController
@RequestMapping("/")
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }


    @PostMapping("/sign-up")
    public ResponseModel<User> registration(@RequestBody RegistrationRequest registrationRequest) {

        User user = signUpService.signUp(registrationRequest);

        if (user == null) {
            return new ResponseModel.ResponseModelBuilder<User>().
                    success(false).message("This email already exists!").
                    data(null).httpStatus(HttpStatus.BAD_REQUEST).build();
        } else {
            return new ResponseModel.ResponseModelBuilder<User>().
                    success(true).message("OK").
                    data(user).httpStatus(HttpStatus.OK).build();
        }
    }

    @PostMapping("/verify-eMail")
    public ResponseModel<JwtResponse> verifyEmail(@RequestBody ConfirmationToken confirmationToken) {

        JwtResponse jwtResponse = signUpService.verifyEMail(confirmationToken);

        if (jwtResponse == null)
            return new ResponseModel.ResponseModelBuilder<JwtResponse>().
                    success(false).message("Confirmation Token is incorrect!").
                    data(null).httpStatus(HttpStatus.BAD_REQUEST).build();

        return new ResponseModel.ResponseModelBuilder<JwtResponse>().
                success(true).message("Email verification has been successfully passed").
                data(jwtResponse).httpStatus(HttpStatus.OK).build();
    }

    @PostMapping("/refresh-token")
    public ResponseModel<JwtResponse> refreshToken() {
        return null;
    }

}
