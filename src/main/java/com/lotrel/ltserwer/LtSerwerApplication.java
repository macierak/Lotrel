package com.lotrel.ltserwer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableWebSecurity
public class LtSerwerApplication  {

	public static void main(String[] args) {
		SpringApplication.run(LtSerwerApplication.class, args);
	}
	@Bean
	public OpenAPI ApiConfig() {
		return new OpenAPI()
				.info(new Info().title("Lotrel API").version("v0.0.1"));
	}
}
