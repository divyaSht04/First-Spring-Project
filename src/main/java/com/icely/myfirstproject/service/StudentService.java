package com.icely.myfirstproject.service;

import com.icely.myfirstproject.model.Student;
import com.icely.myfirstproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        Optional<Student> student = studentRepository.findById(id);
        System.out.println(student.get().getId());
        return student.orElse(null);
    }

    public void editStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudentById(int id) {
        studentRepository.deleteById(id);
    }

}
