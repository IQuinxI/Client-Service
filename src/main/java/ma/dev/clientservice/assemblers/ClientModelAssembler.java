package ma.dev.clientservice.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import ma.dev.clientservice.controllers.ClientController;
import ma.dev.clientservice.models.Client;

@Component
public class ClientModelAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>>{

    @Override
    public EntityModel<Client> toModel(Client client) {
        return EntityModel.of(client,
        linkTo(methodOn(ClientController.class).getOneClient(client.getClientId())).withSelfRel(),
        linkTo(methodOn(ClientController.class).getAllClients()).withRel("Clients")
        );
    }
    
}
