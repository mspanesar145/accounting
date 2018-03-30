package com.accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.accounting.repository.UserRepository;


@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "com.accounting" },basePackageClasses = {
		SpringSecurityConfig.class, UserRepository.class })
public class AccountingApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AccountingApplication.class, args);
	}
}