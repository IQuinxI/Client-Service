package ma.dev.clientservice.controllers;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import ma.dev.clientservice.models.Client;

public interface IClientController {
    CollectionModel<EntityModel<Client>> getAllClients();

    ResponseEntity<?> newClient(Client newClient);

    EntityModel<Client> getOneClient(Long id);

    ResponseEntity<?> replaceClient(Client newClient, Long id);

    ResponseEntity<?> deleteClient(Long id);
}
