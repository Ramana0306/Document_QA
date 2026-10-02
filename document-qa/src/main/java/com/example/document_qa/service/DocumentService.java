package com.example.document_qa.service;

import org.springframework.stereotype.Service;

@Service
public class DocumentService {
    private String documentText;

    public void saveDocument(String text) {
        this.documentText = text;
    }

    public String getDocumentText() {
        return documentText;
    }

    public boolean hasDocument() {
        return documentText != null && !documentText.isBlank();
    }

}
