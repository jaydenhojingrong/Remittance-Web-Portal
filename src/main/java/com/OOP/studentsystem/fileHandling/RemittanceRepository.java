package com.OOP.studentsystem.fileHandling;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository                                        //name of model  // type of pri key
public interface RemittanceRepository extends JpaRepository<EverywhereRemit, Integer> {

//    this enables the JPA
}