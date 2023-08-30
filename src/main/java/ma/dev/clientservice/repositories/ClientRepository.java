package ma.dev.clientservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dev.clientservice.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
