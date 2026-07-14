package com.example.smtp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReminderService {

    @Autowired
    private EmailService emailService;

    public void sendReminder() {

        emailService.sendSavedEmail(2);

    }

}