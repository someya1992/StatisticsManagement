package com.org.stats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StatisticsManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsManagementApplication.class, args);
	}

}
