package pl.pasechnik.productCatalogueTess;

import org.junit.jupiter.api.Test;
import pl.pasechnik.productCatalogue.HashMapProductStorage;
import pl.pasechnik.productCatalogue.Product;
import pl.pasechnik.productCatalogue.ProductCatalogue;
import pl.pasechnik.productCatalogue.ProductStorage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/*public class HashMapProductStorageTest {
    @Test
    void isSaveAndLoadProduct(){
        //Arrange
        ProductCatalogue catalogue=thereIsProductCatalogue();
        var product=catalogue.createProducts("Lego set 8083","nice one");;
        ProductStorage storage = new HashMapProductStorage();
        //Act
        storage.save(product);
        var loaded=storage.loadProductById(product.getId());
        //Assert
        assertEquals(product.getId(),loaded.getId());
        assertEquals(product.getDesc(),loaded.getDesc());
    }
    private  ProductStorage thereIsStorage(){
        return new HashMapProductStorage();
    }
    @Test
    void itLoadsAllProducts(){
        var product=thereIsProduct();
        ProductStorage storage = thereIsStorage();
        storage.save(product);
        List<Product> all = storage.allProducts();
        assertEquals(1,all.size());
    }

}
hi*/