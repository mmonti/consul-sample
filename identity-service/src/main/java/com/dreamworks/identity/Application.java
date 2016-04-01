package com.dreamworks.identity;

import com.dreamworks.identity.model.Identity;
import com.dreamworks.identity.repository.IdentityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.consul.discovery.RibbonConsulAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(IdentityRepository identityRepository) {
		return (args) -> {
			Identity identity = new Identity();
			identity.setEmailAddress("mauro.monti@dreamworks.com");
			identity.setFirstName("Mauro");
			identity.setLastName("Monti");
			identity.setRevoked(false);
			identityRepository.save(identity);

			identity = new Identity();
			identity.setEmailAddress("test@dreamworks.com");
			identity.setFirstName("Test");
			identity.setLastName("Test");
			identity.setRevoked(true);
			identityRepository.save(identity);
		};
	}
}
