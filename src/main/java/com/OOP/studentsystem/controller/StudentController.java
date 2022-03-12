package com.OOP.studentsystem.controller;

import com.OOP.studentsystem.model.Student;
import com.OOP.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.OOP.studentsystem.excel.UserExcelImporter;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
//    for api controlling
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String add(@RequestBody Student student){
        studentService.saveStudent(student);
        return "new student is added";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @RequestMapping("/a")
    @ResponseBody
    public String test () {
        return "test";
    }

    @RequestMapping("/import")
    @ResponseBody
    public List<Student> importFromExcel () {

        UserExcelImporter excelImporter = new UserExcelImporter();
        List<Student> listTest = excelImporter.excelImport();

        int count = 1;
        for (Student  studentElement: listTest){
            studentService.saveStudent(studentElement);
            System.out.println(count);
            count++;
        }

        return listTest;


    }

}


