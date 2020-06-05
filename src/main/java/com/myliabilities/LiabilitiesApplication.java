package com.myliabilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myliabilities.dao.AStartTableCheck;

@SpringBootApplication
public class LiabilitiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiabilitiesApplication.class, args);
		new AStartTableCheck();
	}

}
