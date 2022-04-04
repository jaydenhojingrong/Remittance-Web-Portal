package com.OOP.remittancesystem.service;

import org.springframework.stereotype.Service;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
	

	public ArrayList<String> extractHeaders(String fileDownloadUrl ) {

		String fullFileName = "header.csv";

        //create file of the local csv file (in root folder)
        File file = new File(fullFileName);

        //create new inputstream of the server side csv file
        //e.g. localhost:8080/files/everywhereRemit.csv
        //also create outputStream of the local file (in the root folder)
        try (InputStream input = new URL(fileDownloadUrl).openStream();
            FileOutputStream out = new FileOutputStream(file);) {

            file.deleteOnExit();
            IOUtils.copy(input, out);

        } catch (FileNotFoundException e) {
            // handle exception here
        } catch (IOException e) {
            // handle exception here
        }

		ArrayList<String> headers = new ArrayList<>();

		try (Scanner fIn = new Scanner(file, "UTF-8");){
			while (fIn.hasNext()) {
					String currentLine = fIn.nextLine();	
					Scanner scHeaders = new Scanner(currentLine);
					scHeaders.useDelimiter(",|\r\n|\n");
				while (scHeaders.hasNext()){
					headers.add(scHeaders.next());
				}
				scHeaders.close();
				break;
				}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return headers;
	}

	private String next() {
		return null;
	}

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
		return headerDAO.findFirstBySsotHeaderAndCompanyOrderByCompany(ssotHeader, company);
	}

	public List <String> findBySsotHeader(String ssotHeader) {
		List <HeaderNames> headerNames = headerDAO.findBySsotHeader(ssotHeader);
		List <String> output = new ArrayList<String>();
		for (HeaderNames headerName:headerNames){
			output.add(headerName.getCurrentHeader());
		}
		return output;
	}

	public String getSizeByApiHeaderAndCompany(String apiHeader, String company){
		return headerDAO.findFirstByApiHeaderAndCompany(apiHeader, company).getSize();
	}

	public String getRegexByApiHeaderAndCompany(String apiHeader, String company){
		return headerDAO.findFirstByApiHeaderAndCompany(apiHeader, company).getRegex();
		
		
	}
}