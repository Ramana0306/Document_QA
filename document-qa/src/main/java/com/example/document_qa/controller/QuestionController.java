package com.example.document_qa.controller;

import com.example.document_qa.dto.QuestionRequest;
import com.example.document_qa.service.AiService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final AiService aiService;

    public QuestionController(AiService aiService) {
        this.aiService = aiService;
    }

    @PostMapping
    public String askQuestion(@RequestBody QuestionRequest request) {

        return aiService.askQuestion(request.getQuestion());
    }
}