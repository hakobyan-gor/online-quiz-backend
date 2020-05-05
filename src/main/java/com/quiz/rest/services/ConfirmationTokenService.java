package com.quiz.rest.services;

import com.quiz.models.ConfirmationToken;

public interface ConfirmationTokenService {

    Boolean checkConfirmationToken(ConfirmationToken confirmationToken);

    void saveConfirmationToken(ConfirmationToken confirmationToken);

    void deleteConfirmationTokenByUserId(Long userId);

}
