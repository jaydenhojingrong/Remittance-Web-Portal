package com.OOP.studentsystem.repository;

import com.OOP.studentsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository                                        //name of model  // type of pri key
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    this enables the JPA
}
