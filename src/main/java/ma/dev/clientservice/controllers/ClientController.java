package ma.dev.clientservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.services.ClientService;

@RestController
@RequestMapping(path = "/api")
public class ClientController implements IClientController{

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    @Override
    public CollectionModel<EntityModel<Client>> getAllClients() {
        return clientService.getClients();
    }

    @GetMapping("/test")
    public String test() {
        return clientService.test();
    }
    
}
