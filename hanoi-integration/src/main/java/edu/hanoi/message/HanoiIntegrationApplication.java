package edu.hanoi.message;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:config.xml")
public class HanoiIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HanoiIntegrationApplication.class, args);
	}
}
