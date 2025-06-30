package pl.mpyvovarov.ecommerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.mpyvovarov.productCatalogue.ProductCatalogue;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate http;

    @Autowired
    ProductCatalogue catalog;

    @Test
    void checkoutHappyPath(){
        //Arrange
        String productId=thereIsProduct("Product X", BigDecimal.valueOf(11));
        var toBeCalledUrl = getBaseUrl(String.format("/api/add-product",productId));
        //Act
        http.postForEntity(toBeCalledUrl,null,null);
        http.postForEntity(toBeCalledUrl,null,null);

        var toBeCalledUrlOffer = getBaseUrl("/api/current-offer");
        ResponseEntity<Offer> offerHttp = http.getForEntity(toBeCalledUrlOffer, Offer.class);

        assertEquals(BigDecimal.valueOf(22),offerHttp.getBody().getTotal());
    }
    @Test
    void emptyOffer(){
        //Arrange
        var toBeCalledUrlOffer = getBaseUrl("/api/current-offer");
        ResponseEntity<Offer> offerHttp = http.getForEntity(toBeCalledUrlOffer, Offer.class);

        assertEquals(BigDecimal.ZERO,offerHttp.getBody().getTotal());
    }

    private String thereIsProduct(String name, BigDecimal bigDecimal) {
        var id = catalog.createProducts(name,"nice one");
        catalog.changePrice(id,bigDecimal);
        return id;
    }

    private String getBaseUrl(String productEndpoint) {
        return String.format("http://localhost:%s%s", port, productEndpoint);
    }
}
