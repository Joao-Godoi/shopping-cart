package br.com.joaogodoi.cart.mockitounittests.services;

import br.com.joaogodoi.cart.entities.Client;
import br.com.joaogodoi.cart.mockitounittests.mocks.MockClient;
import br.com.joaogodoi.cart.repositories.ClientRepository;
import br.com.joaogodoi.cart.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ClientServicesTest {
    
    MockClient inputObject;
    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    void setUp() throws Exception {
        inputObject = new MockClient();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Client client = inputObject.mockEntity(1);
        client.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        var response = clientService.findById(1L);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getName());
        assertNotNull(response.getEmail());
        assertNotNull(response.getPhone());
        assertEquals("Client 1", response.getName());
    }

    @Test
    void testFindAll() {
        List<Client> clientList = inputObject.mockEntityList();
        when(clientRepository.findAll()).thenReturn(clientList);

        var response = clientService.findAll();
        assertNotNull(response);
        assertEquals(14, response.size());

        var firstClient = response.get(1);
        assertNotNull(firstClient);
        assertNotNull(firstClient.getId());
        assertNotNull(firstClient.getName());
        assertEquals("Client 1", firstClient.getName());

        var seventhClient = response.get(7);
        assertNotNull(seventhClient);
        assertNotNull(seventhClient.getId());
        assertNotNull(seventhClient.getName());
        assertEquals("Client 7", seventhClient.getName());
    }

    @Test
    void testCreate() {
        Client client = inputObject.mockEntity(1);

        Client persistedClient = client;
        persistedClient.setId(1L);

        Client client2 = inputObject.mockEntity(1);
        client2.setId(1L);

        when(clientRepository.save(client)).thenReturn(persistedClient);

        var response = clientService.createClient(client2);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getName());
        assertEquals("Client 1", response.getName());
    }

    @Test
    void testUpdate() {
        Client client = inputObject.mockEntity(1);
        client.setId(1L);

        Client persistedClient = client;
        persistedClient.setId(1L);

        Client client2 = inputObject.mockEntity(1);
        client2.setId(1L);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(persistedClient);

        var response = clientService.updateClient(1L, client2);
        assertNotNull(response);
        assertNotNull(response.getId());
        assertNotNull(response.getName());
        assertEquals("Client 1", response.getName());
    }
}
