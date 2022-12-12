package br.com.joaogodoi.cart.repositories;

import br.com.joaogodoi.cart.entities.Client;
import br.com.joaogodoi.cart.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    ShoppingCart findByClient(Client client);
}
