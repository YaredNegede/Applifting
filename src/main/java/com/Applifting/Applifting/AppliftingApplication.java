package com.Applifting.Applifting;

import com.Applifting.Applifting.monitor.AppliftHttpTraceRepository;
import com.Applifting.Applifting.monitor.Monitor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Log
@SpringBootApplication
public class AppliftingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AppliftingApplication.class, args);
	}

}
