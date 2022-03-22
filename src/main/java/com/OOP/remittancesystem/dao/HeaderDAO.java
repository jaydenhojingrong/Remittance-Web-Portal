package com.OOP.remittancesystem.dao;

import java.util.List;
import java.util.Optional;

import com.OOP.remittancesystem.entity.HeaderNames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderDAO extends JpaRepository<HeaderNames, String> {

    public List<HeaderNames> findAll();

    public Optional<HeaderNames> findById(String currentHeader);
    
}
