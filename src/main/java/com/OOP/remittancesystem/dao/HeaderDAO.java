package com.OOP.remittancesystem.dao;

import java.util.List;
import java.util.Optional;

import com.OOP.remittancesystem.entity.HeaderNames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderDAO extends JpaRepository<HeaderNames, String> {

    public List<HeaderNames> findAll();

    public List <HeaderNames> findBycurrentHeader(String currentHeader);

    public HeaderNames findByssotHeaderAndCompany(String ssotHeader, String company);

    public HeaderNames save(HeaderNames headerNames);

    
}
