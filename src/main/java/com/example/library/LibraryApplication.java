/**
 * Main Spring Boot Application class for the Library Management System.
 * This class serves as the entry point for the application and includes basic configuration.
 */
package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @SpringBootApplication annotation enables auto-configuration and component scanning
 * @ComponentScan ensures all components in the library package are discovered
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.example.library")
public class LibraryApplication {

	/**
	 * Main method that launches the Spring Boot application
	 * @param args Command line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	/**
	 * Web configuration class to handle static resource serving
	 * Implements WebMvcConfigurer to customize Spring MVC configuration
	 */
	@Configuration
	public static class WebConfig implements WebMvcConfigurer {
		
		/**
		 * Configures static resource handling
		 * Maps requests to /static/** to the classpath:/static/ directory
		 * @param registry ResourceHandlerRegistry to register resource handlers
		 */
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/static/**")
					.addResourceLocations("classpath:/static/");
		}
	}
}
