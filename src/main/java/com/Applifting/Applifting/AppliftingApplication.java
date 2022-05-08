package com.Applifting.Applifting;

import com.Applifting.Applifting.common.MonitorFilter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@SpringBootApplication
public class AppliftingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppliftingApplication.class, args);
	}

	@Bean
	ModelMapper get() {
		return new ModelMapper();
	}
}
