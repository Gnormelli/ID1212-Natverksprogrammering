package com.ID1212.ID1212_Project_Intelij;

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

//	@Bean
//	CommandLineRunner commandLineRunner(DataRepository dataRepository){
//		return args -> {
//			QueuePost maria = new QueuePost("Hemma", "hej", Boolean.TRUE, Boolean.FALSE);
//			dataRepository.save(maria);
//		};
//	}

	/**
	 * IF we want to make cross-origin global for the application
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/postQueue").allowedOrigins("http://localhost:3000");
				registry.addMapping("/getQueue").allowedOrigins("http://localhost:3000");
				registry.addMapping("/login").allowedOrigins("http://localhost:3000");
				registry.addMapping("/perform_login").allowedOrigins("http://localhost:3000");
				registry.addMapping("/create_user").allowedOrigins("http://localhost:3000");
				registry.addMapping("/get_profile_picture").allowedOrigins("http://localhost:3000");
				registry.addMapping("/conversations_by_userID").allowedOrigins("http://localhost:3000");
				registry.addMapping("/group_member_by_conId").allowedOrigins("http://localhost:3000");
				registry.addMapping("/update_profile_picture").allowedOrigins("http://localhost:3000");
				registry.addMapping("/update_membership").allowedOrigins("http://localhost:3000");
				registry.addMapping("/send_message").allowedOrigins("http://localhost:3000");
				registry.addMapping("/get_profile_picture").allowedOrigins("http://localhost:3000");
				registry.addMapping("/get_message").allowedOrigins("http://localhost:3000");
				registry.addMapping("/get_all_conversations").allowedOrigins("http://localhost:3000");
				registry.addMapping("/get_posts").allowedOrigins("http://localhost:3000");
				registry.addMapping("/send_post").allowedOrigins("http://localhost:3000");
				registry.addMapping("/new_conversation").allowedOrigins("http://localhost:3000");


			}
		};
	}

}
