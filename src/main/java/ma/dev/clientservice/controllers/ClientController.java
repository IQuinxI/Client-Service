package ma.dev.clientservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.services.ClientService;

@RestController
public class ClientController implements IClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    @Override
    public CollectionModel<EntityModel<Client>> getAllClients() {
        return clientService.getClients();
    }

    @PostMapping("/clients")
    @Override
    public ResponseEntity<?> newClient(@RequestBody Client newClient) {
        return clientService.newClient(newClient);
    }

    @GetMapping("/clients/{id}")
    @Override
    public EntityModel<Client> getOneClient(@PathVariable(name = "id") Long id) {
        return clientService.getClient(id);
    }

    @PutMapping("/clients/{id}")
    @Override
    public ResponseEntity<?> replaceClient(@RequestBody Client newClient, @PathVariable Long id) {
        return clientService.replaceClient(newClient, id);
    }

    @DeleteMapping("/clients/{id}")
    @Override
    public ResponseEntity<?> deleteClient(@PathVariable(name = "id") Long id) {
        return clientService.deleteClient(id);
    }

}
