package com.quiz.rest.services;

import com.quiz.models.AuthToken;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationTokenService {

    AuthToken createToken(UserDetails userDetails, Long userId);

    AuthToken getAuthTokenByUserId(Long userId);

    AuthToken updateToken(UserDetails userDetails, Long userId);

    void deleteAuthTokenByUserId(Long userId);
}
