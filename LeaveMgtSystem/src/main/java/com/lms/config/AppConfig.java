/**
 * 
 */
package com.lms.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author gurminder.singh
 *
 */
@Configuration
@EnableWebMvc //mvc:annotation-driven
@ComponentScan("com.lms")
public class AppConfig {	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
