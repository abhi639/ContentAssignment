package com.app.configuration;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class Config {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	 
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatterForFieldType(Date.class, new org.springframework.format.datetime.DateFormatter("yyyy-MM-dd"));
//    }
}
