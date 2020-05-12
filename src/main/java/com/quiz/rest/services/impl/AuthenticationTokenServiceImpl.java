package com.quiz.rest.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.rest.services.AuthenticationTokenService;
import com.quiz.rest.repositories.AuthTokenRepository;
import org.springframework.stereotype.Service;
import com.quiz.security.jwt.JwtUtil;
import com.quiz.models.AuthToken;

@Service
public class AuthenticationTokenServiceImpl implements AuthenticationTokenService {

    private final AuthTokenRepository authTokenRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationTokenServiceImpl(AuthTokenRepository authTokenRepository, JwtUtil jwtUtil) {
        this.authTokenRepository = authTokenRepository;
        this.jwtUtil = jwtUtil;
    }

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
