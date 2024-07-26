package com.example.demo.services;


import com.example.demo.exceptions.ResourceAlreadyExistException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.models.Student;
import com.example.demo.repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudent(Long id) throws ResourceNotFoundException{

        Optional<Student> optionalStudent= studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new ResourceNotFoundException("Student with id "+id+ " not found");
        }
        return optionalStudent.get();
    }

    public void addUser(Student student)throws ResourceAlreadyExistException {
        Optional<Student> optionalStudent=studentRepository.findByEmail(student.getEmail());
        if(optionalStudent.isPresent()){
            throw new ResourceAlreadyExistException("");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) throws ResourceNotFoundException{
        Optional<Student> optionalStudent=studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new ResourceNotFoundException("");
        }
        studentRepository.delete(optionalStudent.get());
    }

    @Transactional
    public void updateStudent(Long id,String name)throws Exception{
        Optional<Student> optionalStudent=studentRepository.findById(id);
        if(!optionalStudent.isPresent()){
            throw new ResourceNotFoundException("");
        }
        if(name==null || optionalStudent.get().getEmail().equals(name)){
            throw new Exception("invalid name");
        }
        optionalStudent.get().setName(name);
    }


}
