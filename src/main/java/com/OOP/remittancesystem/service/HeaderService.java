package com.OOP.remittancesystem.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.OOP.remittancesystem.dao.HeaderDAO;
import com.OOP.remittancesystem.entity.EverywhereRemit;
import com.OOP.remittancesystem.entity.FinanceNow;
import com.OOP.remittancesystem.entity.HeaderNames;
import com.OOP.remittancesystem.entity.PaymentGo;
import com.OOP.remittancesystem.entity.Remittance;


@Service
public class HeaderService {

	@Autowired
	private HeaderDAO headerDAO;

	public List<HeaderNames> getAllHeaders() {
		return headerDAO.findAll();
	}


	public HeaderNames getSsotByCurrentHeader(String currentHeader) {
		return headerDAO.findFirstByCurrentHeaderOrderByCompany(currentHeader);
	}

	public HeaderNames getSsotByCurrentHeaderAndCompany(String currentHeader, String company) {
		return headerDAO.findFirstByCurrentHeaderAndCompanyOrderByCompany(currentHeader, company);
	}

	public HeaderNames getApiHeaderBySsotHeaderAndCompany(String ssotHeader, String company) {
		return headerDAO.findBySsotHeaderAndCompany(ssotHeader, company);
	}

	public List <String> findBySsotHeader(String ssotHeader) {
		List <HeaderNames> headerNames = headerDAO.findBySsotHeader(ssotHeader);
		List <String> output = new ArrayList<String>();
		for (HeaderNames headerName:headerNames){
			output.add(headerName.getCurrentHeader());
		}
		return output;
	}
}