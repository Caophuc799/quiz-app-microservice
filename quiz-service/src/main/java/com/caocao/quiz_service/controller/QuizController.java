package com.caocao.quiz_service.controller;

import com.caocao.quiz_service.model.QuestionWrapper;
import com.caocao.quiz_service.model.Quiz;
import com.caocao.quiz_service.model.QuizDto;
import com.caocao.quiz_service.model.Response;
import com.caocao.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @PostMapping("")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return new ResponseEntity<>(quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQuestions(), quizDto.getTitle()), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Quiz>> getQuizes() {
        return new ResponseEntity<>(quizService.getQuizes(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable String id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id), HttpStatus.OK);
    }

    @PostMapping("{id}/submit")
    public ResponseEntity<Integer> submitQuiz(@PathVariable String id, @RequestBody List<Response> quizSubmits) {
        return new ResponseEntity<>(quizService.calculateResult(id, quizSubmits), HttpStatus.OK);
    }

}
