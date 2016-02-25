package com.catalystdevworks.todo;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppRunner {


	public static void main(String[] args) {
		SpringApplication.run(AppRunner.class);
	}

	
}
