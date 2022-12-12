package br.com.joaogodoi.cart.mockitounittests.mocks;

import br.com.joaogodoi.cart.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class MockProduct {

    public Product mockEntity() {
        return mockEntity(0);
    }

    public List<Product> mockEntityList() {
        List<Product> products = new ArrayList<Product>();
        for (int i = 0; i < 14; i++) {
            Product product = mockEntity(i);
            product.setCode((long) i);
            products.add(product);
        }
        return products;
    }

    public Product mockEntity(Integer number) {
        Product product = new Product();
        product.setDescription("Mocked Product " + number);
        return product;
    }

}
