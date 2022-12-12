package br.com.joaogodoi.cart.mockitounittests.services;

import br.com.joaogodoi.cart.entities.Product;
import br.com.joaogodoi.cart.mockitounittests.mocks.MockProduct;
import br.com.joaogodoi.cart.repositories.ProductRepository;
import br.com.joaogodoi.cart.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServicesTest {
    
    MockProduct inputObject;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() throws Exception {
        inputObject = new MockProduct();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Product product = inputObject.mockEntity(1);
        product.setCode(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        var response = productService.findByCode(1L);
        assertNotNull(response);
        assertNotNull(response.getCode());
        assertNotNull(response.getDescription());
        assertEquals("Mocked Product 1", response.getDescription());
    }

    @Test
    void testFindAll() {
        List<Product> productList = inputObject.mockEntityList();
        when(productRepository.findAll()).thenReturn(productList);

        var response = productService.findAll();
        assertNotNull(response);
        assertEquals(14, response.size());

        var firstProduct = response.get(1);
        assertNotNull(firstProduct);
        assertNotNull(firstProduct.getCode());
        assertNotNull(firstProduct.getDescription());
        assertEquals("Mocked Product 1", firstProduct.getDescription());

        var seventhProduct = response.get(7);
        assertNotNull(seventhProduct);
        assertNotNull(seventhProduct.getCode());
        assertNotNull(seventhProduct.getDescription());
        assertEquals("Mocked Product 7", seventhProduct.getDescription());
    }

    @Test
    void testCreate() {
        Product product = inputObject.mockEntity(1);

        Product persistedProduct = product;
        persistedProduct.setCode(1L);

        Product product2 = inputObject.mockEntity(1);
        product2.setCode(1L);

        when(productRepository.save(product)).thenReturn(persistedProduct);

        var response = productService.createProduct(product2);
        assertNotNull(response);
        assertNotNull(response.getCode());
        assertNotNull(response.getDescription());
        assertEquals("Mocked Product 1", response.getDescription());
    }

    @Test
    void testUpdate() {
        Product product = inputObject.mockEntity(1);
        product.setCode(1L);

        Product persistedProduct = product;
        persistedProduct.setCode(1L);

        Product product2 = inputObject.mockEntity(1);
        product2.setCode(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(persistedProduct);

        var response = productService.updateProduct(1L, product2);
        assertNotNull(response);
        assertNotNull(response.getCode());
        assertNotNull(response.getDescription());
        assertEquals("Mocked Product 1", response.getDescription());
    }
}
