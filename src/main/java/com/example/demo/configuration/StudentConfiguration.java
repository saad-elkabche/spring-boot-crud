package com.example.demo.configuration;

import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;


@Configuration
public class StudentConfiguration {


    @Bean
    CommandLineRunner seedStudents(StudentRepository studentRepository){
        return args -> {
            Student student1=new Student(
                    "Saad El",
                    "saad@gmail.com",
                    LocalDate.of(2000,3,1)
            )  ;
            Student student2=new Student(
                    "Ahmed ",
                    "ahmed@gmail.com",
                    LocalDate.of(2002,3,1)
            )  ;
            Student student3=new Student(
                    "khaled ",
                    "khaled@gmail.com",
                    LocalDate.of(2004,3,1)
            )  ;

            List<Student> students=List.of(student1,student2,student3);
            studentRepository.saveAll(students);
        };
    }


}
