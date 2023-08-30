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
          clientRepository.save(new Client(null, "fname1", "lname1", 0612345667l, "contact@email.com"))  ;
          clientRepository.save(new Client(null, "fname2", "lname2", 0612345667l, "contact@email.com"))  ;
        
          clientRepository.findAll().forEach(System.out::println);
          
        };
    }
}
