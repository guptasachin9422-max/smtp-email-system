package com.example.smtp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${spring.mail.username}")
    private String fromEmail;

    // Send Saved Email
    public String sendSavedEmail(Integer id) {

        try {

            Email email = emailRepository.findById(id).orElse(null);

            if (email == null) {
                return "Email Template Not Found";
            }

            List<Student> students = studentRepository.findByStatus("Interested");

            if (students.isEmpty()) {
                return "No Interested Students Found";
            }

            for (Student student : students) {

                SimpleMailMessage mail = new SimpleMailMessage();

                mail.setFrom(fromEmail);
                mail.setTo(student.getEmail());
                mail.setSubject(email.getSubject());
                mail.setText(email.getMessage());

                mailSender.send(mail);

                Thread.sleep(2000);
            }

            return "Email Sent Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR : "
                    + e.getClass().getSimpleName()
                    + "\n"
                    + e.getMessage();
        }
    }

    // Send Single Email
    public String sendEmail(String to, String subject, String message) {

        try {

            SimpleMailMessage mail = new SimpleMailMessage();

            mail.setFrom(fromEmail);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(message);

            mailSender.send(mail);

            return "Email Sent Successfully";

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR : "
                    + e.getClass().getSimpleName()
                    + "\n"
                    + e.getMessage();
        }
    }

}