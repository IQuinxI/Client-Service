package ma.dev.clientservice;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ma.dev.clientservice.controllers.ClientController;
import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.services.ClientService;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

 
    @Test
    public void shouldGetAllClients() throws Exception {
        Client client = new Client(null, "fname5",
                "lname5", "0612345667l", "contact@email.com");

        EntityModel<Client> entityModel = EntityModel.of(client);

        CollectionModel<EntityModel<Client>> collectionModel = CollectionModel
                .of(Collections.singletonList(entityModel));

        given(clientService.getClients()).willReturn(collectionModel);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.clients[0].firstName").value(client.getFirstName())); 
                                                                                                                
    }

     @Test
    public void shouldGetOneClient() throws Exception {
        // Create a mock client
        Client mockClient = new Client();
        mockClient.setClientId(1L);
        mockClient.setFirstName("John");
        mockClient.setLastName("Doe");
        mockClient.setEmail("john.doe@example.com");
        mockClient.setPhoneNumber("1234567890");

        EntityModel<Client> entityModel = EntityModel.of(mockClient);
        // Mock the behavior of the service
        when(clientService.getClient(1L)).thenReturn(entityModel);

        // Perform the GET request
        mockMvc.perform(get("/api/clients/{id}", 1L)
                .accept(MediaTypes.HAL_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientId").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void shouldReplaceClient() throws Exception {
        // Create a mock client
        Client newClient = new Client();
        newClient.setClientId(1L);
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setEmail("john.doe@example.com");
        newClient.setPhoneNumber("1234567890");

        ResponseEntity responseEntity = ResponseEntity
        .created(URI.create("http://localhost:8080/api/clients/"+newClient.getClientId()))
        .body(newClient);
        // Mock the behavior of the service
        when(clientService.replaceClient(newClient, 1L)).thenReturn(responseEntity);

        // Perform the PUT request
        mockMvc.perform(put("/api/clients/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(newClient)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.clientId").value(1L))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void shouldCreateNewClient() throws Exception {
        // Create a new client
        Client newClient = new Client();
        newClient.setFirstName("John");
        newClient.setLastName("Doe");
        newClient.setEmail("john.doe@example.com");
        newClient.setPhoneNumber("1234567890");

        ResponseEntity responseEntity = ResponseEntity
        .created(URI.create("http://localhost:8080/api/clients/"+newClient.getClientId()))
        .body(newClient);
        // Mock the behavior of the service
        when(clientService.newClient(newClient)).thenReturn(responseEntity);

        // Perform the POST request
        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(newClient)))
                .andExpect(status().isCreated())
                .andExpect( jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));
    }

    @Test
    public void shouldDeleteClient() throws Exception {
        
        when(clientService.deleteClient(1l)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(delete("/api/clients/{id}", 1l))
            .andExpect(status().isNoContent());
    }
}
