package ma.dev.clientservice.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ma.dev.clientservice.assemblers.ClientModelAssembler;
import ma.dev.clientservice.controllers.ClientController;
import ma.dev.clientservice.exceptions.ClientNotFoundException;
import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.repositories.ClientRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * ClientService
 */
@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private ClientModelAssembler assembler;

    @Override
    public CollectionModel<EntityModel<Client>> getClients() {
        List<EntityModel<Client>> clients = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clients,
                linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel());
    }

    @Override
    public EntityModel<Client> getClient(Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        return assembler.toModel(client);
    }

    @Override
    public ResponseEntity<?> newClient(Client newClient) {
        EntityModel<Client> entityModel = assembler.toModel(repository.save(newClient));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    // @Override
    // public ResponseEntity<?> newClient(Client newClient) {
    // EntityModel<Client> entityModel =
    // assembler.toModel(repository.save(newClient));

    // return ResponseEntity
    // .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
    // .body(entityModel);
    // }

    @Override
    public ResponseEntity<?> replaceClient(Client newClient, Long id) {
        Client updatedClient = repository.findById(id)
                .map(client -> {
                    client.setEmail(newClient.getEmail());
                    client.setFirstName(newClient.getFirstName());
                    client.setLastName(newClient.getLastName());
                    client.setPhoneNumber(newClient.getPhoneNumber());

                    return repository.save(client);
                })
                .orElseGet(() -> {
                    newClient.setClientId(id);
                    return repository.save(newClient);
                });

        EntityModel<Client> entityModel = assembler.toModel(updatedClient);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Override
    public ResponseEntity<?> deleteClient(Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}