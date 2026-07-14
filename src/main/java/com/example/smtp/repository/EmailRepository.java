package com.example.smtp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.smtp.entity.Email;


@Repository
public interface EmailRepository extends JpaRepository<Email,Integer> {

}