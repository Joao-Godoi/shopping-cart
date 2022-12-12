package br.com.joaogodoi.cart.services;

import br.com.joaogodoi.cart.entities.Product;
import br.com.joaogodoi.cart.exceptions.ResourceNotFoundException;
import br.com.joaogodoi.cart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByCode(Long code) {
        return productRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("No product found for code " + code));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long code, Product product) {
        Product productToUpdate = productRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("No product found for code " + code));
        productToUpdate.setDescription(product.getDescription());
        return productRepository.save(productToUpdate);
    }
}
