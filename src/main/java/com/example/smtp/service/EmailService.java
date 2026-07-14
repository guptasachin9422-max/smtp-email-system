package com.example.smtp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.smtp.entity.Email;
import com.example.smtp.entity.Student;
import com.example.smtp.repository.EmailRepository;
import com.example.smtp.repository.StudentRepository;

@Service
public class EmailService {
    @Autowired
private EmailRepository emailRepository;

@Autowired
private StudentRepository studentRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Single Email
    public String sendSavedEmail(Integer id) {

    Email email = emailRepository.findById(id).orElse(null);

    if (email == null) {
        return "Email Template Not Found";
    }

    List<Student> students = studentRepository.findByStatus("Interested");

    for (Student student : students) {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(student.getEmail());
        mail.setSubject(email.getSubject());
        mail.setText(email.getMessage());

        mailSender.send(mail);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    

    return "Email Sent Successfully";
}
public String sendEmail(String to, String subject, String message) {

    SimpleMailMessage mail = new SimpleMailMessage();

    mail.setTo(to);
    mail.setSubject(subject);
    mail.setText(message);

    mailSender.send(mail);

    return "Email Sent Successfully";
}

}