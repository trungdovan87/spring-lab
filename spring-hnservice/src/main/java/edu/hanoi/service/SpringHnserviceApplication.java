package edu.hanoi.service;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringHnserviceApplication {

	private final  static Logger logger = Logger.getLogger(SpringHnserviceApplication.class);
	public static void main(String[] args) {
		logger.info("starting...");
		SpringApplication.run(SpringHnserviceApplication.class, args);
	}
}
