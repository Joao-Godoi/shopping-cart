package br.com.joaogodoi.cart.services;

import br.com.joaogodoi.cart.entities.Client;
import br.com.joaogodoi.cart.entities.Product;
import br.com.joaogodoi.cart.entities.ShoppingCart;
import br.com.joaogodoi.cart.exceptions.ResourceNotFoundException;
import br.com.joaogodoi.cart.repositories.ClientRepository;
import br.com.joaogodoi.cart.repositories.ProductRepository;
import br.com.joaogodoi.cart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartFactory shoppingCartFactory;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No client found for id " + id));
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client clientData) {
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No client found for id " + id));
        updateData(clientToUpdate, clientData);
        return clientRepository.save(clientToUpdate);
    }

    private void updateData(Client clientToUpdate, Client clientData) {
        clientToUpdate.setName(clientData.getName());
        clientToUpdate.setEmail(clientData.getEmail());
        clientToUpdate.setPhone(clientData.getPhone());
    }

    public ShoppingCart geShoppingCart(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No client found for id " + id));
        List<ShoppingCart> shoppingCarts = client.getShoppingCarts();
        return shoppingCarts.get(shoppingCarts.size() - 1);
    }

    public ShoppingCart addProductOnCart(Long clientId, Long productCode, BigDecimal unitPrice, Integer quantity) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ResourceNotFoundException("No client found for id " + clientId));
        Product product = productRepository.findById(productCode).get();
        ShoppingCart cart;
        if (client.getShoppingCarts().isEmpty()) {
            cart = shoppingCartFactory.create(clientId);
        } else {
            cart = shoppingCartFactory.getShoppingCar(clientId);
            cart.addItem(product, unitPrice, quantity);
        }
        clientRepository.save(client);
        return cart;
    }
}
