package com.quiz.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.quiz.models.AuthToken;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    AuthToken findTokenByUserId(Long userId);

    void deleteAuthTokenByUserId(@Param("user_id") Long userId);

}
