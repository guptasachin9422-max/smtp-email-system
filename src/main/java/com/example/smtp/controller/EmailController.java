package com.example.smtp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smtp.dto.EmailRequest;
import com.example.smtp.entity.Email;
import com.example.smtp.repository.EmailRepository;
import com.example.smtp.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    // Send Single Email
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {

        return emailService.sendEmail(
                request.getTo(),
                request.getSubject(),
                request.getMessage()
        );
    }
    @PostMapping("/send/{id}")
public String sendSavedEmail(@PathVariable Integer id) {

    return emailService.sendSavedEmail(id);

}

    // Save Email Template
    @PostMapping("/save")
    public Email saveEmail(@RequestBody Email email) {
        return emailRepository.save(email);
    }

    // Get All Templates
    @GetMapping("/all")
    public List<Email> getAllEmails() {
        return emailRepository.findAll();
    }
}