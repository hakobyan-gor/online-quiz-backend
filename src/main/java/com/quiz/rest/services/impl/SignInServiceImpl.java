package com.quiz.rest.services.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.rest.services.AuthenticationTokenService;
import org.springframework.stereotype.Service;
import com.quiz.models.response.ResponseModel;
import com.quiz.models.request.LoginRequest;
import com.quiz.models.response.JwtResponse;
import com.quiz.rest.services.SignInService;
import org.springframework.http.HttpStatus;
import com.quiz.rest.services.UserService;
import com.quiz.models.AuthToken;
import com.quiz.enums.Status;
import com.quiz.models.User;

@Service
public class SignInServiceImpl implements SignInService {

    private final UserService userService;
    private final AuthenticationTokenService authenticationTokenService;

    @Autowired
    public SignInServiceImpl(UserService userService, AuthenticationTokenService authenticationTokenService) {
        this.userService = userService;
        this.authenticationTokenService = authenticationTokenService;
    }


    @Override
    public ResponseModel<?> signIn(LoginRequest loginRequest) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(loginRequest.getUsername());

        ResponseModel<?> responseModel = new ResponseModel.ResponseModelBuilder<>().success(false).
                data(null).message("").httpStatus(HttpStatus.BAD_REQUEST).build();

        if (user == null){
            responseModel.setMessage("Username Not Found");
            return responseModel;
        }

        if (!new BCryptPasswordEncoder(8).matches(loginRequest.getPassword(), user.getPassword())){
            responseModel.setMessage("Password is Incorrect!");
            return responseModel;
        }

        if (user.getStatus().equals(Status.PENDING)){
            responseModel.setMessage("You must confirm your email, before access to our web site.");
            return responseModel;
        }

        userService.updateUserStatusBeforeLogIn(user.getId());

        AuthToken token = authenticationTokenService.getAuthTokenByUserId(user.getId());

        return new ResponseModel.ResponseModelBuilder<JwtResponse>().success(true).
                data(new JwtResponse(token.getToken(), "Bearer", user.getUsername())).
                message("Success").httpStatus(HttpStatus.OK).build();

    }

}
