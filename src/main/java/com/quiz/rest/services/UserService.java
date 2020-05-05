package com.quiz.rest.services;

import com.quiz.models.request.LoginRequest;
import com.quiz.models.request.RegistrationPasswordRequest;
import com.quiz.models.User;

public interface UserService {

    User createUser(User user);

    Boolean existsUser(String username);

    Boolean existsUserByEmail(String eMail);

    Boolean existsUserByUsernameAndPassword(String username, String password);

    Boolean authenticatedUser(String username);

    User getUser(LoginRequest loginRequest);

    User findUserByUsername(String username);

    User getUserById(Long id);

    User updateUser(User user);

    User updateUser(RegistrationPasswordRequest request);

}
