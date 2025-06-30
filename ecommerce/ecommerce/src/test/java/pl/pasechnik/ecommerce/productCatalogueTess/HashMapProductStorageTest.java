package pl.pasechnik.ecommerce.productCatalogueTess;

import org.junit.jupiter.api.Test;
import pl.pasechnik.productCatalogue.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
public class HashMapProductStorageTest {

    @Test
    void itSaveAndLoadProduct() {
        //Arrange
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        //Act
        storage.save(product);
        var loaded = storage.loadProductById(product.getId());

        //Assert
        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getDesc(), loaded.getDesc());
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "test it", "desc");
    }

    private ProductStorage thereIsStorage() {
        return HashMapProductStorage();
    }

    private ProductStorage HashMapProductStorage() {
        return new HashMapProductStorage();
    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);

        List<Product> all = storage.allProducts();

        assertEquals(1, all.size());
    }
}
