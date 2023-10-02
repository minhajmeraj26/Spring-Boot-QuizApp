package com.telusko.quizapp.controller;


import com.telusko.quizapp.model.QuestionResponseDto;
import com.telusko.quizapp.service.MainQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quizApp/question")
public class QuestionContoller {

    @Autowired
    MainQuestionService mainQuestionService;

    // Function for getting all questions from the service
    @GetMapping("allQuestions")
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions()
    {
        System.out.println("Inside getAllQuestions Controller");
        return mainQuestionService.getAllQuestions();
    }

    // Function for getting questions from the service using category
    @GetMapping("categories/{category}")
    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByCategory(@PathVariable String category)
    {
        System.out.println("Inside getQuestionsByCategory Controller");
        return mainQuestionService.getQuestionsByCategory(category);
    }

    // Function for inserting a new question into the database
    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody QuestionResponseDto questionRequestDto)
    {
        System.out.println("Inside addQuestion Controller");
        return mainQuestionService.addQuestion(questionRequestDto);
    }

}



