package ma.dev.clientservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.dev.clientservice.assemblers.ClientModelAssembler;
import ma.dev.clientservice.controllers.ClientController;
import ma.dev.clientservice.models.Client;
import ma.dev.clientservice.repositories.ClientRepository;
import ma.dev.clientservice.services.ClientService;

@WebMvcTest(ClientController.class)
class ClientServiceApplicationTests {
	@MockBean
	private ClientRepository clientRepository;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService service;

	@MockBean
	private ClientModelAssembler assembler;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateClient() throws Exception {
		// Client client = new Client(null, "fname5",
		// 		"lname5", 0612345667l, "contact@email.com");

		// when(service.newClient(client)).thenReturn(null, null);
		mockMvc.perform(post("/api/clients").contentType(MediaType.APPLICATION_JSON)
		.content("{\"firstName\": \"fname3\"," + //
				"\"lastName\": \"lname3\"," + //
				"\"phoneNumber\": 103402423," + //
				"\"email\": \"contact@email.com\"}"))
		.andExpect(status().isCreated())
		.andExpect(header().exists("Location"))
		.andDo(print());

		verify(service).newClient(any(Client.class));
	}

	@Test
	void shouldReturnClients() throws Exception {

		List<Client> clients = List.of(new Client(null, "fname5",
				"lname5", 0612345667l, "contact@email.com"));

		List<EntityModel<Client>> clientsEntityModels = clients.stream()
				.map(assembler::toModel)
				.collect(Collectors.toList());

		CollectionModel<EntityModel<Client>> collectionModel = CollectionModel.of(clientsEntityModels,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).getAllClients())
						.withSelfRel());

		when(service.getClients())
				.thenReturn(collectionModel);
		mockMvc.perform(get("/api/clients").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$._embedded").exists())
				.andDo(print());
	}

}
