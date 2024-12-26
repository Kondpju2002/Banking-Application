package com.cts.hcbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cts.hcbanking")
@EnableJpaRepositories(basePackages = "com.cts.hcbanking.dao")
public class HcBankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcBankingBackendApplication.class, args);
	}

}
	