package com.OOP.studentsystem.fileHandling;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HeaderServiceImpl implements HeaderService{

    private HeaderRepository headerRepository;

    public HeaderServiceImpl(HeaderRepository headerRepository) {
		super();
		this.headerRepository = headerRepository;
	}

    @Override
	public List<HeaderNames> getAllHeaders() {
		return headerRepository.findAll();
	}

    @Override
	public HeaderNames getHeaderByCurrentHeader(String currentHeader) {
		return headerRepository.findById(currentHeader).get();
	}

}