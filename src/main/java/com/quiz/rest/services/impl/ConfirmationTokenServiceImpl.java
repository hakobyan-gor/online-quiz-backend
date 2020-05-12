package com.quiz.rest.services.impl;

import com.quiz.rest.repositories.ConfirmationTokenRepository;
import com.quiz.rest.services.ConfirmationTokenService;
import org.springframework.stereotype.Service;
import com.quiz.models.ConfirmationToken;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public Boolean checkConfirmationToken(ConfirmationToken confirmationToken) {
        return confirmationTokenRepository.existsConfirmationTokenByConfirmationTokenAndUserId(
                confirmationToken.getConfirmationToken(),
                confirmationToken.getUserId());
    }

    @Override
    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    @Override
    public void deleteConfirmationTokenByUserId(Long userId) {
        confirmationTokenRepository.deleteConfirmationTokenByUserId(userId);
    }
}
