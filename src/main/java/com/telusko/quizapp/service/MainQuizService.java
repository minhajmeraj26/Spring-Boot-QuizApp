package com.telusko.quizapp.service;

import com.telusko.quizapp.model.QuestionResponseDto;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.QuizResponses;
import com.telusko.quizapp.repositoryDao.QuestionDao;
import com.telusko.quizapp.repositoryDao.QuizDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MainQuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<QuestionResponseDto> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz Quiz = new Quiz();
        Quiz.setTitle(title);
        Quiz.setQuestions(questions);
        quizDao.save(Quiz);

        return new ResponseEntity<String>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<QuestionResponseDto> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();

        for (QuestionResponseDto question : questionsFromDB)
        {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getQuesid(), question.getQuestionTitle(), question.getOption1(),question.getOption2(), question.getOption3(), question.getOption4());
            questionForUser.add(questionWrapper);
        }
        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<QuizResponses> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<QuestionResponseDto> questions = quiz.getQuestions();
        int right = 0;
        int i = 0;
        for(QuizResponses response: responses)
        {
            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }

        return new ResponseEntity<>(right , HttpStatus.OK);
    }
}
