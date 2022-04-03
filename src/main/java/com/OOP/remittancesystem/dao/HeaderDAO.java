package com.OOP.remittancesystem.dao;

import java.util.List;

import com.OOP.remittancesystem.entity.HeaderNames;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderDAO extends JpaRepository<HeaderNames, String> {

    public List<HeaderNames> findAll();

    // public Optional<HeaderNames> findById(String currentHeader);

    // public HeaderNames findByCurrentHeader(String currentHeader);

    public HeaderNames findFirstByCurrentHeaderOrderByCompany(String currentHeader);

    public HeaderNames findFirstByCurrentHeaderAndCompanyOrderByCompany(String currentHeader, String company);

    public HeaderNames findFirstBySsotHeaderAndCompanyOrderByCompany(String ssotHeader, String company);

    public List <HeaderNames> findBySsotHeader(String ssotHeader);

    public HeaderNames save(HeaderNames headerNames);
    
}
