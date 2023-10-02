package com.telusko.quizapp.controller;

import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.QuizResponses;
import com.telusko.quizapp.service.MainQuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("quizApp/quiz")
public class QuizController {

    @Autowired
    MainQuizService mainQuizService;

    // Function for getting all questions from the service
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title)
    {
        System.out.println("Inside createQuiz Controller");
        return mainQuizService.createQuiz(category,numQ,title);
    }

    // Function for getting all questions from the service
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id)
    {
        System.out.println("Inside getQuizQuestion Controller");
        return mainQuizService.getQuizQuestion(id);
    }

    // Function for getting all questions from the service
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<QuizResponses> responses)
    {
        System.out.println("Inside submitQuiz Controller");
        return mainQuizService.calculateResult(id,responses);
    }

}
