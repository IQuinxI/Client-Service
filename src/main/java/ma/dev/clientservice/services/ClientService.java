package ma.dev.clientservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import ma.dev.clientservice.assemblers.ClientModelAssembler;
import ma.dev.clientservice.controllers.ClientController;
import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.repositories.ClientRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * ClientService
 */
@Service
public class ClientService {

    @Autowired 
    private ClientRepository repository;

    @Autowired
    private ClientModelAssembler assembler;

    public CollectionModel<EntityModel<Client>> getClients() {
        List<EntityModel<Client>> clients =  repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());
        
            return CollectionModel.of(clients, 
                linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel());
    }    

    public String test() {
        return "Hello";
    }

}