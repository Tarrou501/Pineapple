package br.senai.suico.RestauranteX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@SpringBootApplication

public class RestauranteXApplication implements WebMvcConfigurer
{

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT");

		// registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("GET",
		// "POST", "DELETE","OPTIONS", "PUT");

	}

	public static void main(String[] args) {
		SpringApplication.run(RestauranteXApplication.class, args);
	}

}
