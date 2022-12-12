package br.com.joaogodoi.cart.services;

import br.com.joaogodoi.cart.entities.Client;
import br.com.joaogodoi.cart.entities.ShoppingCart;
import br.com.joaogodoi.cart.repositories.ClientRepository;
import br.com.joaogodoi.cart.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
@Service
public class ShoppingCartFactory {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     * <p>
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param clientId
     * @return ShoppingCart
     */
    public ShoppingCart create(Long clientId) {
        Client client = clientRepository.findById(clientId).get();
        List<ShoppingCart> shoppingCarts = client.getShoppingCarts();
        shoppingCarts.add(new ShoppingCart());
        clientRepository.save(client);
        return shoppingCarts.get(shoppingCarts.size() - 1);
    }

    public ShoppingCart getShoppingCar(Long clientId) {
        Client client = clientRepository.findById(clientId).get();
        List<ShoppingCart> shoppingCarts = client.getShoppingCarts();
        return shoppingCarts.get(shoppingCarts.size() - 1);
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getAverageTicketAmount() {
        BigDecimal total = new BigDecimal("0.0");
        List<ShoppingCart> allShoppingCarts = shoppingCartRepository.findAll();
        for (ShoppingCart shoppingCart : allShoppingCarts) {
            total.add(shoppingCart.getAmount());
        }
        return total.divide(new BigDecimal(allShoppingCarts.size()));
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param clientId
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidate(String clientId) {
        return false;
    }
}
