package com.quiz.rest.controllers;

import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.LogOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogOut {

    private LogOutService logOutService;

    @Autowired
    public LogOut(LogOutService logOutService) {
        this.logOutService = logOutService;
    }

    @PatchMapping("/log-out/user/id/{userId}")
    public void logOut(@PathVariable("userId") Long id){
        logOutService.logOut(id);
    }

}
