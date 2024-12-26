package com.caocao.question_service.controller;

import com.caocao.question_service.model.Question;
import com.caocao.question_service.model.QuestionWrapper;
import com.caocao.question_service.model.Response;
import com.caocao.question_service.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("")
    public ResponseEntity<List<Question>> getQuestions(@RequestParam(value = "category", required = false) String category) {
        try {
            if (category != null && !category.isEmpty()) {
                return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
            }
            return new ResponseEntity<>(questionService.getQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return new ResponseEntity<>(questionService.addQuestion(question), HttpStatus.CREATED);
    }

    @PostMapping("generate")
    public ResponseEntity<List<String>> getQuestionsForQuiz(@RequestBody Map<String, Object> requestBody) {
        String category = (String) requestBody.get("category");
        int numQuestions = (int) requestBody.get("numQuestions");
        if (category == null || category.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(questionService.getQuestionsForQuiz(category, numQuestions), HttpStatus.CREATED);
    }

    @PostMapping("quiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<String> questionIds) {

        return new ResponseEntity<>(questionService.getQuestionsFromIds(questionIds), HttpStatus.CREATED);
    }

    @PostMapping("calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return new ResponseEntity<>(questionService.getScore(responses), HttpStatus.OK);
    }
}
