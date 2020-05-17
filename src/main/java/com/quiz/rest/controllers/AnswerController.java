package com.quiz.rest.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.quiz.models.response.ResponseModel;
import com.quiz.rest.services.AnswerService;
import javax.ws.rs.core.MediaType;
import com.quiz.models.Answer;
import java.util.List;
import javax.ws.rs.*;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes(MediaType.APPLICATION_JSON)
@Api(value = "Answer Controller")
@Path("/answers")
@RestController
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Path("/add-list")
    @POST
    public ResponseModel<List<Answer>> createQuestions(List<Answer> answerList){
        return answerService.createAnswers(answerList);
    }

    @Path("/add")
    @POST
    public ResponseModel<Answer> createQuestion(Answer answer){
        return answerService.createAnswer(answer);
    }

    @Path("/get/question/{id}")
    @GET
    public ResponseModel<List<Answer>> getAnswersByQuestionId(@PathParam("id") Long id){
        return answerService.getAnswersByQuestionId(id);
    }

    @Path("/get/{id}")
    @GET
    public ResponseModel<Answer> getAnswer(@PathParam("id") Long id){
        return answerService.getAnswerById(id);
    }

    @Path("/update/{id}")
    @PUT
    public ResponseModel<Answer> updateAnswer(@PathParam("id") Long id, Answer answer){
        answer.setId(id);
        return answerService.updateAnswer(answer);
    }

}