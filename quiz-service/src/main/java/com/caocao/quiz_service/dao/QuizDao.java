package com.caocao.quiz_service.dao;

import com.caocao.quiz_service.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, String> {

}
