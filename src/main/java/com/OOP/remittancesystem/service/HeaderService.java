package com.OOP.remittancesystem.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

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

	public String getApiHeaderBySsotHeaderAndCompany(String ssotHeader, String company) {
		return headerDAO.findFirstBySsotHeaderAndCompanyOrderByCompany(ssotHeader, company).getApiHeader();
	}

	public List <String> findBySsotHeader(String ssotHeader) {
		// return headerDAO.findBySsotHeader(ssotHeader);
		List <HeaderNames> headerNames = headerDAO.findBySsotHeader(ssotHeader);
		List <String> output = new ArrayList<String>();
		for (HeaderNames headerName:headerNames){
			output.add(headerName.getCurrentHeader());
		}
		return output;
	}

	public Map<String, String> listToMapRemittance(List<Remittance> remittanceList){
		System.out.println("\n\n\n\n\n\n");
		Map<String, String> remittanceMap = new HashMap<String, String>();
		// System.out.println(Arrays.deepToString(remittanceList.toArray()));
		for (Remittance remittance:remittanceList){
			if (remittance instanceof EverywhereRemit){
				System.out.println("everywhere here");
				EverywhereRemit currentRemit = (EverywhereRemit) remittance;
				System.out.println(currentRemit.getrMobileNumber());
				System.out.println(currentRemit.getsFirstName());
			}
			else if (remittance instanceof FinanceNow){
				System.out.println("finance here");
				FinanceNow currentRemit = (FinanceNow) remittance;
				System.out.println(currentRemit.getrNationality());
				System.out.println(currentRemit.getsFirstName());
			}
			else if (remittance instanceof PaymentGo){
				System.out.println("payment here");
				PaymentGo currentRemit = (PaymentGo) remittance;
				System.out.println(currentRemit.getsAccountNumber());
				System.out.println(currentRemit.getsFirstName());
			}
		
		}
		return remittanceMap;
	}
	
}