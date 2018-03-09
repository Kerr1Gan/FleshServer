package com.shaw.fleshServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.shaw.fleshServer.mapper")
public class FleshServerApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(FleshServerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FleshServerApplication.class);
	}
}
