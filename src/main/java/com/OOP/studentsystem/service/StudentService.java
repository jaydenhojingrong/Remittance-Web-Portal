package com.OOP.studentsystem.service;

import com.OOP.studentsystem.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    public Student saveStudent(Student student);

    public List<Student> getAllStudents();

}
