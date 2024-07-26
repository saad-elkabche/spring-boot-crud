package com.example.demo.controllers;

import com.example.demo.exceptions.ResourceAlreadyExistException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Student;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> index(){
        return studentService.getAllStudents();
    }


    @GetMapping(path = "{studentId}")
    public ResponseEntity<?> show(@PathVariable("studentId") Long studentId){
        try{
            Student student=studentService.getStudent(studentId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>("Student Not Found",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student){
        try{
            studentService.addUser(student);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }catch (ResourceAlreadyExistException ex){
            return new ResponseEntity<>("Student Alredy Exist",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<?> delete(@PathVariable("studentId") Long studentId){
      try{
          studentService.deleteStudent(studentId);
          return new ResponseEntity<>("Success",HttpStatus.OK);
      }catch (ResourceNotFoundException ex){
          return new ResponseEntity<>("Student Not Found",HttpStatus.NOT_FOUND);
      }
    }


    @PutMapping(path = "{studentId}")
    public ResponseEntity<?> update(@PathVariable("studentId")Long id,
                                    @RequestParam(value = "name",required = false) String name){

        try {
            studentService.updateStudent(id,name);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>("Student Not Found",HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            return new ResponseEntity<>("invalid name",HttpStatus.NOT_ACCEPTABLE);
        }

    }





}
