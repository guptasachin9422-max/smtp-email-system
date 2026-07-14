package com.example.smtp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.smtp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findByStatus(String status);

}