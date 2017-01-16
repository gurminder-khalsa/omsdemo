/**
 * 
 */
package com.lms.config;

import java.io.IOException;
import java.util.Properties;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.velocity.VelocityEngineFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author gurminder.singh
 *
 */
@Configuration
@EnableWebMvc //mvc:annotation-driven
@ComponentScan("com.lms")
@PropertySource("classpath:lms-mail.properties")
public class AppConfig {	
	
	@Autowired
	private Environment environment;
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
    public JavaMailSender getMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
         
        //Using gmail
        mailSender.setHost(environment.getRequiredProperty("lms.smtp.host"));
        mailSender.setPort(Integer.parseInt(environment.getRequiredProperty("lms.smtp.port")));
        mailSender.setUsername(environment.getRequiredProperty("lms.smtp.username"));
        mailSender.setPassword(environment.getRequiredProperty("lms.smtp.password"));
         
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");//Prints out everything on screen
         
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
	
	 @Bean
	    public VelocityEngine getVelocityEngine() throws VelocityException, IOException {
	        VelocityEngineFactory factory = new VelocityEngineFactory();
	        Properties props = new Properties();
	        props.put("resource.loader", "class");
	        props.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
	 
	        factory.setVelocityProperties(props);
	        return factory.createVelocityEngine();
	    }
	
}
