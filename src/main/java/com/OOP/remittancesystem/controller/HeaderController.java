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
	public List <HeaderNames> getHeaderByCurrentHeader(@PathVariable String currentHeader) {
		return headerService.getHeaderByCurrentHeader(currentHeader);
	}

	@RequestMapping(value = "/addHeader", method = RequestMethod.POST)
	@ResponseBody
	public HeaderNames insertHeaderNames(@RequestParam String currentHeader, @RequestParam String ssotHeader, @RequestParam String company){
		HeaderNames headername = new HeaderNames(currentHeader,ssotHeader, company, "dummyy");
		return headerDAO.save(headername);
	}


	/**
	 * Get all Header by SSOT Header
	 * @param ssotheader of a column of the uploaded csv file
	 * @return HeaderName Entity of the indicated currentHeader
	 */
    @GetMapping("/headers/api/{ssotHeader}/{company}")
	public HeaderNames getApiHeaderBySSOTHeader(@PathVariable String ssotHeader, @PathVariable String company) {
		return headerService.getApiHeaderBySSOTHeader(ssotHeader, company);
	}


}
