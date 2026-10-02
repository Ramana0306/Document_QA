package com.example.document_qa.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final ChatClient chatClient;
    private final DocumentService documentService;

    public AiService(
            ChatClient.Builder chatClientBuilder,
            DocumentService documentService) {

        this.chatClient = chatClientBuilder.build();
        this.documentService = documentService;
    }

    public String askQuestion(String question) {

        if (!documentService.hasDocument()) {
            return "Please upload a PDF document first.";
        }

        String documentText = documentService.getDocumentText();

        String prompt = """
                You are a document question-answering assistant.

                Answer the user's question ONLY using the information
                available in the document below.

                If the answer cannot be found in the document,
                clearly say:
                "The answer is not available in the uploaded document."

                Do not use outside knowledge.

                DOCUMENT:
                %s

                USER QUESTION:
                %s
                """.formatted(documentText, question);

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }
}