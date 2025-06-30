package pl.pasechnik.ecommerce.productCatalogueTess;

import org.junit.jupiter.api.Test;
import pl.pasechnik.productCatalogue.*;

import java.util.List;
import java.util.UUID;
/*
import static org.junit.jupiter.api.Assertions.*;
public class HashMapProductStorageTest {
    @Test
   void isSaveAndLoadProduct(){
        //Arrange
        ProductCatalogue catalogue=thereIsProductCatalogue();
        var product=thereIsProduct();;
        ProductStorage storage = thereIsStorage();
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
    private  ProductCatalogue thereIsProductCatalogue(){return new DbProductCatalogue();}
    private Product thereIsProduct(){return new Product(UUID.randomUUID(),"Lego set 8083","nice one");}
    @Test
    void itLoadsAllProducts(){
        var product=thereIsProduct();
        ProductStorage storage = thereIsStorage();
        storage.save(product);
        List<Product> all = storage.allProducts();
        assertEquals(1,all.size());
    }

}
*/