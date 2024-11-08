package com.example.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
 
@SpringBootApplication
@RestController
public class BackApplication {
	
	@GetMapping("/")
	public String Index(){
		return "Ejemlos";
	}

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
