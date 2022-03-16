package com.OOP.studentsystem.fileHandling;

// import org.springframework.stereotype.Service;
import java.util.List;

public interface HeaderService {

    // public HeaderNames saveHeaders(HeaderNames headers);

    public List<HeaderNames> getAllHeaders();

    public HeaderNames getHeaderByCurrentHeader(String currentHeader);

}
