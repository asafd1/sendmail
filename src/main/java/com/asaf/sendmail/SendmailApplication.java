package com.asaf.sendmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SendmailApplication {
	public static void main(String[] args) {
		SpringApplication.run(SendmailApplication.class, args);
		System.out.println("SendmailApplication started...");
	}
}
