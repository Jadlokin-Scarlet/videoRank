package com.tilitili.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("*")
//				.allowedHeaders("*")
				.allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
				.maxAge(3600)
				.allowCredentials(true);
	}

}
