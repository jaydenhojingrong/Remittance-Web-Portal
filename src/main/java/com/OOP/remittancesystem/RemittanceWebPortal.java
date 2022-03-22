package com.OOP.remittancesystem;

import com.OOP.remittancesystem.fileHandling.FileStorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class RemittanceWebPortal {

	public static void main(String[] args) {
		SpringApplication.run(RemittanceWebPortal.class, args);
	}

}
