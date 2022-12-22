package com.ID1212.ID1212_Project_Intelij;

import com.ID1212.ID1212_Project_Intelij.DataAccess.QueuePostRepository;
import com.ID1212.ID1212_Project_Intelij.Models.QueuePost;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Id1212ProjectIntelijApplication {

	public static void main(String[] args) {
		SpringApplication.run(Id1212ProjectIntelijApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(QueuePostRepository queuePostRepository){
		return args -> {
			QueuePost maria = new QueuePost("Hemma", "hej", Boolean.TRUE, Boolean.FALSE);
			queuePostRepository.save(maria);
		};
	}

	/**
	 * IF we want to make cross origin global for the application
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/getQueue").allowedOrigins("http://localhost:3000");
			}
		};
	}

}
