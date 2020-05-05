package com.quiz.rest.repositories;

import com.quiz.models.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    Boolean existsConfirmationTokenByConfirmationTokenAndUserId(int confirmationToken, Long userId);

    @Transactional
    void deleteConfirmationTokenByUserId(Long userId);

}
