package com.OOP.remittancesystem.service;

import org.springframework.stereotype.Service;
import java.util.List;

import com.OOP.remittancesystem.dao.HeaderDAO;
import com.OOP.remittancesystem.entity.HeaderNames;

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