package br.com.joaogodoi.cart.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
@Entity
@Table(name = "td_shopping_cart")
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "cart")
    private final List<Item> items = new ArrayList<>();

    public ShoppingCart() {
    }

    public ShoppingCart(Long id, Client client) {
        this.id = id;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Permite a adição de um novo item no carrinho de compras.
     * <p>
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     * <p>
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public void addItem(Product product, BigDecimal unitPrice, int quantity) {
        try {
            Item itemToAdd = new Item(null, unitPrice, quantity, product, this);
            if (items.isEmpty()) {
                items.add(itemToAdd);
            } else {
                for (Item item : items) {
                    if (!item.getProduct().equals(product)) {
                        items.add(itemToAdd);
                        break;
                    } else {
                        item.setQuantity(item.getQuantity() + quantity);
                        if (!Objects.equals(item.getUnitPrice(), unitPrice)) item.setUnitPrice(unitPrice);
                    }
                }
            }
        } catch (RuntimeException e) {
//            throw new AddItemOnShoppingCartException("Error trying to add an item to cart, please try again.");
            throw new RuntimeException("Error trying to add an item to cart, please try again.");
        }
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param product
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(Product product) {
        return this.items.remove(product);
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na
     * coleção, em que zero representa o primeiro item.
     *
     * @param itemIndex
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(int itemIndex) {
        Item item = this.items.remove(itemIndex);
        return item != null;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        BigDecimal amount = new BigDecimal("0.0");
        for (Item item : this.items) {
            amount.add(item.getAmount());
        }
        return amount;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public List<Item> getItems() {
        return items;
    }
}
