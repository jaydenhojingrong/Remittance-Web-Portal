package com.OOP.studentsystem.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.OOP.studentsystem.dao.HeaderDAO;
import com.OOP.studentsystem.entity.HeaderNames;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class HeaderService {

	@Autowired
	private HeaderDAO headerDAO;

	public List<HeaderNames> getAllHeaders() {
		return headerDAO.findAll();
	}

	public HeaderNames getHeaderByCurrentHeader(String currentHeader) {
		return headerDAO.findById(currentHeader).get();
	}

}