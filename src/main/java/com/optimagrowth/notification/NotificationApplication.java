package com.optimagrowth.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@RefreshScope
@EnableMethodSecurity
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

}
