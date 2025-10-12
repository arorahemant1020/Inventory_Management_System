package com.ncuindia.edu.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigurationApplication {
	
	public static void main(String[] args) {
		// String mode = System.getenv("CONFIG_MODE");
		// SpringApplication app = new SpringApplication(ConfigurationApplication.class);

		// if("native".equalsIgnoreCase(mode)){
		// 	app.setAdditionalProfiles("native");
		// 	System.out.println(mode);
		// } else{
		// 	app.setAdditionalProfiles("git");
		// 	System.out.println(mode);
		// }


		// app.run(args);
		SpringApplication.run(ConfigurationApplication.class, args);
	}

}
