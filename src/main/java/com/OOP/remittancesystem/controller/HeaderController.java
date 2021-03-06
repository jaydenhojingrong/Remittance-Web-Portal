package com.OOP.remittancesystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.OOP.remittancesystem.dao.HeaderDAO;
import com.OOP.remittancesystem.entity.HeaderNames;
import com.OOP.remittancesystem.service.HeaderService;

@RestController
public class HeaderController {

    @Autowired
    private HeaderService headerService;

	@Autowired
    private HeaderDAO headerDAO;
    // public HeaderController(HeaderDAO headerService){
    //     super();
    //     this.headerService = headerService;
    // }

    // handler method to handle list headers and return mode and view
	/**
	 * Get all headers
	 * @return List of headers
	 */
	@GetMapping("/headers")
	@CrossOrigin(origins = "http://localhost:3000")
	public List <HeaderNames> getAllHeaders(HeaderNames headerNames) {
		return headerService.getAllHeaders();
	}

	/**
	 * Get all headers by currentHeader
	 * @param currentHeader of a column of the uploaded csv file
	 * @return HeaderName Entity of the indicated currentHeader
	 */
    @GetMapping("/headers/{currentHeader}")
	@CrossOrigin(origins = "http://localhost:3000")
	public HeaderNames getHeaderByCurrentHeader(@PathVariable String currentHeader) {
		return headerService.getSsotByCurrentHeader(currentHeader);
	}

	@GetMapping("/headers/{currentHeader}/{company}")
	public HeaderNames getHeaderByCurrentHeader(@PathVariable String currentHeader, @PathVariable String company) {
		return headerService.getSsotByCurrentHeaderAndCompany(currentHeader, company);
	}

	@GetMapping("/headers/getCurrentHeader/{ssotHeader}")
	public List <String> getCurrentHeaderBySsot(@PathVariable String ssotHeader) {
		return headerService.findBySsotHeader(ssotHeader);
	}

	@GetMapping("/headers/getApiHeader/{ssotHeader}/{company}")
	@CrossOrigin(origins = "http://localhost:3000")
	public HeaderNames getCurrentHeaderBySsot(@PathVariable String ssotHeader, @PathVariable String company) {
		return headerService.getApiHeaderBySsotHeaderAndCompany(ssotHeader, company);
	}

	@RequestMapping(value = "/addHeader", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public HeaderNames insertHeaderNames(@RequestParam String currentHeader, @RequestParam String ssotHeader, @RequestParam String company, @RequestParam String apiHeader){
		HeaderNames headername = new HeaderNames(currentHeader,ssotHeader, company, apiHeader, "", "");
		return headerDAO.save(headername);
	}

	
	@GetMapping("/headers/getSize/{apiHeader}/{company}")
	public String getSizeByApiHeaderAndCompany(@PathVariable String apiHeader, @PathVariable String company) {
		return headerService.getSizeByApiHeaderAndCompany(apiHeader, company);
	}

	@GetMapping("/headers/getRegex/{apiHeader}/{company}")
	public String getRegexByApiHeaderAndCompany(@PathVariable String apiHeader, @PathVariable String company) {
		return headerService.getRegexByApiHeaderAndCompany(apiHeader, company);
	}
}
