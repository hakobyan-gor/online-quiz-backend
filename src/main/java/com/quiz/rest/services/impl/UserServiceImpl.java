package com.quiz.rest.services.impl;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import com.quiz.models.request.RegistrationPasswordRequest;
import com.quiz.rest.repositories.AuthTokenRepository;
import com.quiz.rest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import com.quiz.models.request.LoginRequest;
import com.quiz.rest.services.UserService;
import com.quiz.models.User;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthTokenRepository authTokenRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthTokenRepository authTokenRepository) {
        this.userRepository = userRepository;
        this.authTokenRepository = authTokenRepository;
    }

    @Override
    public User createUser(User user) {
        System.out.println(user);
        user.setPassword(new BCryptPasswordEncoder(8).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Boolean existsUser(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public Boolean existsUserByEmail(String eMail) {
        return userRepository.existsUserByUsername(eMail);
    }

    @Override
    public Boolean existsUserByUsernameAndPassword(String username, String password) {
        return userRepository.existsUserByUsernameAndPassword(username, password);
    }

    @Override
    public Boolean authenticatedUser(String username) {
        return userRepository.authenticatedUser(username);
    }

    @Override
    public User getUser(LoginRequest loginRequest) {
        return userRepository.findUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }

    @Override
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(RegistrationPasswordRequest request) {
        User user = userRepository.findUserById(request.getId());
        user = userRepository.save(user);
        return user;
    }

}
