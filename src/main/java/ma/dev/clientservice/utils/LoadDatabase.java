package ma.dev.clientservice.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.repositories.ClientRepository;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner load(ClientRepository clientRepository) {
        return args -> {
            clientRepository.save(new Client(1L,"Joey","Tribianni","123456789","Joey@tribianni"));
            clientRepository.save(new Client(2L,"Rachel","Green","123456789","Joey@tribianni"));
            clientRepository.save(new Client(3L,"Ross","Geller","123456789","Joey@tribianni"));
          clientRepository.findAll().forEach(System.out::println);
          
        };
    }
}
