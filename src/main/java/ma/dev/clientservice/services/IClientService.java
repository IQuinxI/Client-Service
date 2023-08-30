package ma.dev.clientservice.services;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import ma.dev.clientservice.models.Client;

public interface IClientService {
    CollectionModel<EntityModel<Client>> getClients();
    EntityModel<Client> getClient(Long id);
    ResponseEntity<?> newClient(Client newClient);
    ResponseEntity<?> replaceClient(Client newClient, Long id);
    ResponseEntity<?> deleteClient(Long id);
}
