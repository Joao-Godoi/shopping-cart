package br.com.joaogodoi.cart.mockitounittests.mocks;

import br.com.joaogodoi.cart.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class MockClient {

    public Client mockEntity() {
        return mockEntity(0);
    }

    public List<Client> mockEntityList() {
        List<Client> clients = new ArrayList<Client>();
        for (int i = 0; i < 14; i++) {
            Client client = mockEntity(i);
            client.setId((long) i);
            clients.add(client);
        }
        return clients;
    }

    public Client mockEntity(Integer number) {
        Client client = new Client();
        client.setName("Client " + number);
        client.setEmail("client" + number + "@mocked.com");
        client.setPhone("123456789");
        return client;
    }

}
