package com.quiz.rest.services.impl;

import com.quiz.models.AuthToken;
import com.quiz.rest.repositories.AuthTokenRepository;
import com.quiz.rest.services.AuthenticationTokenService;
import com.quiz.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenServiceImpl implements AuthenticationTokenService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthToken createToken(UserDetails userDetails, Long userId) {

        AuthToken authToken = new AuthToken();
        authToken.setToken(jwtUtil.generateToken(userDetails));
        authToken.setUserId(userId);

        authTokenRepository.save(authToken);

        return authToken;
    }

    @Override
    public AuthToken getAuthTokenByUserId(Long userId) {
        return authTokenRepository.findTokenByUserId(userId);
    }


    @Override
    public AuthToken updateToken(UserDetails userDetails, Long userId) {

        AuthToken authToken = new AuthToken();
        authToken.setToken(jwtUtil.generateToken(userDetails));
        authToken.setUserId(userId);

        deleteAuthTokenByUserId(userId);

        authTokenRepository.save(authToken);

        return authToken;
    }

    @Override
    public void deleteAuthTokenByUserId(Long userId){
        authTokenRepository.deleteAuthTokenByUserId(userId);
    }

}
