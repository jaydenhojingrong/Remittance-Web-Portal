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

	public List<HeaderNames> getHeaderByCurrentHeader(String currentHeader) {
		return headerDAO.findBycurrentHeader(currentHeader);
	}

	public HeaderNames getApiHeaderBySSOTHeader(String ssotHeader, String company) {
		return headerDAO.findByssotHeaderAndCompany(ssotHeader, company);
	}

}