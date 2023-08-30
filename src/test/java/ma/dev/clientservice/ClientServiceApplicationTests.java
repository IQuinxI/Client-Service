package ma.dev.clientservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Autowired
	private ObjectMapper objectMapper;

	
	// @Test
	// void shouldCreateClient() throws Exception {
	// 	Client client = new Client(null, "fname5",
	// 			"lname5", 0612345667l, "contact@email.com");

	// 	mockMvc.perform(post("/clients").contentType(MediaType.APPLICATION_JSON)
	// 	.content(objectMapper.writeValueAsString(client)))
	// 	.andExpect(status().isCreated())
	// 	.andDo(print());

	// }

	@Test
	void shouldReturnClients() throws Exception	 {
		mockMvc.perform(get("/api/clients").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print());		 
	}
	
}
