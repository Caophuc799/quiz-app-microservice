package com.caocao.quiz_service.feign;

import com.caocao.quiz_service.model.QuestionWrapper;
import com.caocao.quiz_service.model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @PostMapping("questions/generate")
    public ResponseEntity<List<String>> getQuestionsForQuiz(@RequestBody Map<String, Object> requestBody);

    @PostMapping("questions/quiz")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<String> questionIds);

    @PostMapping("questions/calculateScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
