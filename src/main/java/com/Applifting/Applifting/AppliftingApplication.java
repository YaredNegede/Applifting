package com.Applifting.Applifting;

import com.Applifting.Applifting.common.MonitorFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AppliftingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AppliftingApplication.class, args);
	}

	@Bean
	ModelMapper get() {
		return new ModelMapper();
	}

	@Autowired
	MonitorFilter logInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(logInterceptor);
	}
}
