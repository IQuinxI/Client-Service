package ma.dev.clientservice;

import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ClientServiceApplication  {

	@Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientServiceApplication.class, args);
	}













/*
	@Bean
	public CommandLineRunner commandLineRunner(ClientRepository clientRepository) {
		return args -> {
			clientRepository.saveAll(
					List.of(
							Client.builder().firstName("Joey").lastName("Tribianni").email("joey@tribianni").phoneNumber("12345678"),
							Client.builder().firstName("Rachel").lastName("Green").email("Rachel@Green").phoneNumber("12345678"),
							Client.builder().firstName("Ross").lastName("Geller").email("Ross@Geller").phoneNumber("12345678")
					)
			);


		};
	}



 */

}