package pl.mpyvovarov.ecommerce.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mpyvovarov.ecommerce.sales.cart.HashMapCartStorage;
import pl.mpyvovarov.ecommerce.sales.reservation.ReservationRepository;
import pl.mpyvovarov.ecommerce.sales.reservation.SpyPaymentGateway;
import pl.mpyvovarov.productCatalogue.ArrayProductStorage;
import pl.mpyvovarov.productCatalogue.ProductCatalogue;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {
    ProductCatalogue catalog;

    @BeforeEach
    void setup(){
        catalog = new ProductCatalogue((new ArrayProductStorage()));
    }
    @Test
    void itShowsEmptyOffer(){
        SalesFacade sales = thereIsSalesModule();
        String customerId = ThereIsCustomer("Kuba");

        Offer offer = sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.ZERO,offer.getTotal());
    }
/*
    @Test
    void itAllowsToCollectProducts(){
        SalesFacade sales = thereIsSalesModule();
        String customerId = ThereIsCustomer("Kuba");
        String productId= thereIsProduct("Product X",BigDecimal.valueOf(10));

        sales.addToCart(customerId,productId);
        Offer offer =sales.getCurrentOffer(customerId);

        assertEquals(BigDecimal.valueOf(10),offer.getTotal());
    }
    @Test
    void itAllowsToCollectProductsSeparately(){
        SalesFacade sales = thereIsSalesModule();
        String customerId1 = ThereIsCustomer("Kuba");
        String customerId2 = ThereIsCustomer("Michal");
        String productId= thereIsProduct("Product X",BigDecimal.valueOf(10));

        sales.addToCart(customerId1,productId);
        sales.addToCart(customerId2,productId);
        Offer offer1 =sales.getCurrentOffer(customerId1);
        Offer offer2 =sales.getCurrentOffer(customerId2);


        assertEquals(BigDecimal.valueOf(10),offer1.getTotal());
        assertEquals(BigDecimal.valueOf(20),offer2.getTotal());
    }

 */
/*
    @Test
    void offerAcceptance(){
        SalesFacade sales = thereIsSalesModule();
        String customerId = ThereIsCustomer("Kuba");
        String productId= thereIsProduct("Product X",BigDecimal.valueOf(10));

        sales.addToCart(customerId,productId);
        Offer offer =sales.getCurrentOffer(customerId);

        ReservationDetails details=sales.AcceptOrder(
                new AcceptOfferCommand()
                        .setFname("Kuba")
                        .setLname("Kanclerz")
                        .setEmail("smath")
        );
    }
*/

    private String thereIsProduct(String productX, BigDecimal bigDecimal) {
        var id= catalog.createProducts(productX,"desc");
        catalog.changePrice(id,bigDecimal);
        return id;

    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new SpyPaymentGateway(),
                new ReservationRepository()
        );
    }

    private String ThereIsCustomer(String customerName) {
        return String.format("customer__%s",customerName);
    }
}
