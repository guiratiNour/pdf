package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.PdfFileService;

@SpringBootApplication
public class PdfFileCreationApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PdfFileCreationApplication.class, args);
		
	}

}
