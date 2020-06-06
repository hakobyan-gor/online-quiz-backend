package com.quiz.rest.services.impl;

import com.quiz.rest.services.LogOutService;
import com.quiz.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogOutServiceImpl implements LogOutService {

    private UserService userService;

    @Autowired
    public LogOutServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void logOut(Long id) {
        userService.updateUserStatusBeforeLogOut(id);
    }

}
