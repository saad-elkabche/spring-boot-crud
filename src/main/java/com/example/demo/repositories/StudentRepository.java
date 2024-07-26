package com.example.demo.repositories;

import com.example.demo.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {


     Optional<Student> findByEmail(String email);

}
