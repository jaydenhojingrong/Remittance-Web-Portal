package com.OOP.studentsystem.fileHandling;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class HeaderController {

    @Autowired
    private HeaderService headerService;

    public HeaderController(HeaderService headerService){
        super();
        this.headerService = headerService;
    }

    // handler method to handle list headers and return mode and view
	/**
	 * Get all headers
	 * @return List of headers
	 */
	@GetMapping("/headers")
	public List <HeaderNames> listHeaders(HeaderNames headerNames) {
		return headerService.getAllHeaders();
	}

	/**
	 * Get all headers by currentHeader
	 * @param currentHeader of a column of the uploaded csv file
	 * @return HeaderName Entity of the indicated currentHeader
	 */
    @GetMapping("/headers/{currentHeader}")
	public HeaderNames listHeader(@PathVariable String currentHeader) {
		return headerService.getHeaderByCurrentHeader(currentHeader);
	}

}
