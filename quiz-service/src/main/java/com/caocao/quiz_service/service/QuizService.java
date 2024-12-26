package com.caocao.quiz_service.service;

import com.caocao.quiz_service.dao.QuizDao;
import com.caocao.quiz_service.feign.QuizInterface;
import com.caocao.quiz_service.model.QuestionWrapper;
import com.caocao.quiz_service.model.Quiz;
import com.caocao.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public String createQuiz(String category, int numQ, String title) {
        Map<String, Object> data = new HashMap<>();
        data.put("category", category);
        data.put("numQuestions", numQ);
        List<String> questions = quizInterface.getQuestionsForQuiz(data).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return "Success";
    }

    public List<QuestionWrapper> getQuizQuestions(String id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<String> questionIds = quiz.get().getQuestionIds();
        List<QuestionWrapper> questions = quizInterface.getQuestionsFromIds(questionIds).getBody();

        return questions;
    }

    public List<Quiz> getQuizes() {
        return quizDao.findAll();
    }

    public Integer calculateResult(String id, List<Response> quizSubmits) {
        Integer score = quizInterface.getScore(quizSubmits).getBody();
        return score;
    }
}
