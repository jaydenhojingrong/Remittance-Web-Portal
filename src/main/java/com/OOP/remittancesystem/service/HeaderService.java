package com.OOP.remittancesystem.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.OOP.remittancesystem.dao.HeaderDAO;
import com.OOP.remittancesystem.entity.HeaderNames;


@Service
public class HeaderService {

	@Autowired
	private HeaderDAO headerDAO;

	public List<HeaderNames> getAllHeaders() {
		return headerDAO.findAll();
	}

	// public HeaderNames getHeaderByCurrentHeader(String currentHeader) {
	// 	return headerDAO.findById(currentHeader).get();
	// }

	// public HeaderNames getSsotByCurrentHeader(String currentHeader) {
	// 	return headerDAO.findByCurrentHeader(currentHeader);
	// }

	public HeaderNames getSsotByCurrentHeader(String currentHeader) {
		return headerDAO.findFirstByCurrentHeaderOrderByCompany(currentHeader);
	}

}