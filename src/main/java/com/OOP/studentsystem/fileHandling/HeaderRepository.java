package com.OOP.studentsystem.fileHandling;

import com.OOP.studentsystem.entity.HeaderNames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                          //name of model  // type of pri key
public interface HeaderRepository extends JpaRepository<HeaderNames, String> {


}



