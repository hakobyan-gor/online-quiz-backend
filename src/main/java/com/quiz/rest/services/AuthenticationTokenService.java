package com.quiz.rest.services;

import org.springframework.security.core.userdetails.UserDetails;
import com.quiz.models.AuthToken;

public interface AuthenticationTokenService {

    AuthToken createToken(UserDetails userDetails, Long userId);

    AuthToken getAuthTokenByUserId(Long userId);

    AuthToken updateToken(UserDetails userDetails, Long userId);

    void deleteAuthTokenByUserId(Long userId);
}
