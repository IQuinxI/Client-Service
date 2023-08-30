package ma.dev.clientservice.controllers;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import ma.dev.clientservice.models.Client;

public interface IClientController {
    CollectionModel<EntityModel<Client>> getAllClients();
}
