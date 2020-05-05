package com.quiz.rest.services.impl;

import com.quiz.enums.Status;
import com.quiz.models.User;
import com.quiz.models.request.LoginRequest;
import com.quiz.models.AuthToken;
import com.quiz.models.response.JwtResponse;
import com.quiz.rest.services.AuthenticationTokenService;
import com.quiz.rest.services.SignInService;
import com.quiz.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> signIn(LoginRequest loginRequest) throws UsernameNotFoundException {

        Map<String, Object> message = new HashMap<>();

        User user = userService.findUserByUsername(loginRequest.getUsername());

        if (user == null){
            message.put("not found", "Username Not Found");
            return message;
        }

        if (!new BCryptPasswordEncoder(8).matches(loginRequest.getPassword(), user.getPassword())){
            message.put("error", "Password is Incorrect!");
            return message;
        }

        if (user.getStatus().equals(Status.PENDING)){
            message.put("error", "You must confirm your email, before access to our web site.");
            return message;
        }

        AuthToken token = authenticationTokenService.getAuthTokenByUserId(user.getId());

        message.put("success", new JwtResponse(token.getToken(), "Bearer", user.getUsername()));
        return message;
    }

}
