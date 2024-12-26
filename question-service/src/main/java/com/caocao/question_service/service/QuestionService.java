package com.caocao.question_service.service;

import com.caocao.question_service.dao.QuestionDao;
import com.caocao.question_service.model.Question;
import com.caocao.question_service.model.QuestionWrapper;
import com.caocao.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public List<String> getQuestionsForQuiz(String category, Integer numQuestions) {
        List<String> questions = questionDao.findRandomQuestionsByCategory(category, numQuestions);
        return questions;
    }

    public List<QuestionWrapper> getQuestionsFromIds(List<String> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (String id: questionIds) {
            questions.add((questionDao.findById(id).get()));
        }

        for (Question question: questions) {
            QuestionWrapper wrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            wrappers.add(wrapper);
        }
        return wrappers;
    }

    public Integer getScore(List<Response> responses) {
        int right = 0;
        for (Response qs: responses) {
            Question question = questionDao.findById(qs.getId()).get();
            if (qs.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }
        return right;
    }
}
