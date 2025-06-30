package pl.mpyvovarov.ecommerce.productCatalogueTess;

import org.junit.jupiter.api.Test;
import pl.mpyvovarov.productCatalogue.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogueTest {
    @Test
    void itAllowsToListAllProducts(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        List<Product> products=catalogue.allProducts();

        assertTrue (products.isEmpty());
    }

    @Test
    void itAllowsToCreateProducts(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        String productId=catalogue.createProducts("Lego set 8083","nice one");
        List<Product> products=catalogue.allProducts();
        assertFalse (products.isEmpty());
    }
    @Test
    void createProductsIsIdentifiable(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        String productId1=catalogue.createProducts("Lego set 8083","nice one");
        String productId2=catalogue.createProducts("Lego set 6285","nice one");
        assertNotEquals(productId1,productId2);
    }
    @Test
    void itLoadsProductById(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        String productId=catalogue.createProducts("Lego set 8083","nice one");
        Product loaded=catalogue.loadProductById(productId);
        assertEquals(productId,loaded.getId());
        assertEquals("Lego set 8083",loaded.getName());
        assertEquals("nice one",loaded.getName());
    }
    @Test
    void allowsToApplyPrice(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        String productId=catalogue.createProducts("Lego set 8083","nice one");
        catalogue.changePrice(productId,BigDecimal.valueOf(100.10));
        Product loaded=catalogue.loadProductById(productId);
        assertEquals(BigDecimal.valueOf(100.10),loaded.getPrice());
    }
    @Test
    void denyToAllowToApplyPriceThatViolatesMinimumRange(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        String productId=catalogue.createProducts("Lego set 8083","nice one");
        assertThrows(
                InvalidPriceExeption.class,
                ()->catalogue.changePrice(productId,BigDecimal.valueOf(-10))
        );
    }
    @Test
    void allowsToApplyImage(){
        ProductCatalogue catalogue=thereIsProductCatalogue();
        String productId=catalogue.createProducts("Lego set 8083","nice one");
        catalogue.changeImage(productId,"https://picsum.photos/id/237/200/300");
        Product loaded=catalogue.loadProductById(productId);
        assertEquals("https://picsum.photos/id/237/200/300",loaded.getImage());
    }
    private ProductCatalogue thereIsProductCatalogue(){
        return new ProductCatalogue(
                new ArrayProductStorage()
        );
    }
}
