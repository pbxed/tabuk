package com.pbx.tabuk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TabukApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabukApplication.class, args);
	}

}
