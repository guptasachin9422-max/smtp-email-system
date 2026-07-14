package com.example.smtp.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.smtp.service.ReminderService;

@Component
public class EmailScheduler {

    @Autowired
    private ReminderService reminderService;

    @Scheduled(fixedDelay = 86400000) // 1 Day = 24 Hours
    public void reminderMail() {

        reminderService.sendReminder();

    }

}