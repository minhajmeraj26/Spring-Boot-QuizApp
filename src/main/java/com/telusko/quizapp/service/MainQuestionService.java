package com.telusko.quizapp.service;

import com.telusko.quizapp.model.QuestionResponseDto;
import com.telusko.quizapp.repositoryDao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MainQuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {

        try {
            System.out.println("Inside getAllQuestions MainQuestionService");

            List<QuestionResponseDto> ques = new ArrayList<QuestionResponseDto>();

            questionDao.findAll().forEach(q1 -> ques.add(q1));

//        ques = questionDao.getAllQuestion();
            System.out.println("Size of ques Lists : " + ques.size());

            if (ques.size() > 0) {
                return new ResponseEntity<>(ques.stream().filter(Objects::nonNull).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ques, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<QuestionResponseDto>> getQuestionsByCategory(String category) {
        try {
            System.out.println("Inside getQuestionsByCategory MainQuestionService");

            List<QuestionResponseDto> ques = new ArrayList<QuestionResponseDto>();

            ques = questionDao.findByCategory(category);
            System.out.println("Size of ques Lists : " + ques.size());

            if (ques.size() > 0) {
                return new ResponseEntity<>(ques.stream().filter(Objects::nonNull).collect(Collectors.toList()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ques, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String>  addQuestion(QuestionResponseDto questionRequestDto) {
        System.out.println("Inside addQuestion MainQuestionService");
        System.out.println("QUES ID : " + questionRequestDto.getQuesid());
//        questionRequestDto.setQuesid(6);
//        questionDao.addQuestion(questionRequestDto);
        questionDao.save(questionRequestDto);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
}