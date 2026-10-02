package com.example.document_qa.controller;

import com.example.document_qa.service.DocumentService;
import com.example.document_qa.service.PdfService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final PdfService pdfService;
    private final DocumentService documentService;

    public DocumentController(
            PdfService pdfService,
            DocumentService documentService) {

        this.pdfService = pdfService;
        this.documentService = documentService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public String uploadDocument(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        String text = pdfService.extractText(file);

        documentService.saveDocument(text);

        return "Document uploaded successfully";
    }
}