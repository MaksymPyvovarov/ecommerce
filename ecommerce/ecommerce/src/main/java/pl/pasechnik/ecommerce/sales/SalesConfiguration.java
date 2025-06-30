package pl.pasechnik.ecommerce.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.pasechnik.ecommerce.payu.*;
import pl.pasechnik.ecommerce.sales.cart.HashMapCartStorage;
import pl.pasechnik.ecommerce.sales.payment.PaymentDetails;
import pl.pasechnik.ecommerce.sales.payment.PaymentGateway;
import pl.pasechnik.ecommerce.sales.payment.RegisterPaymentRequest;
import pl.pasechnik.ecommerce.sales.reservation.ReservationRepository;

import java.util.Arrays;
import java.util.UUID;

@Configuration
public class SalesConfiguration {
    @Bean
    SalesFacade createSales() {
        return new SalesFacade(
                new HashMapCartStorage(),
                new OfferCalculator(),
                new PayUPayementGateway(new PayU(new RestTemplate(), PayUConfiguration.sandbox("300746","2ee86a66e5d97e3fadc400c9f19b065d"))),
                new ReservationRepository()
        );
    }
    class PayUPayementGateway implements PaymentGateway {
        private PayU payU;
        public PayUPayementGateway(PayU payU) {
            this.payU = payU;
        }
        @Override
        public PaymentDetails registerPayment(RegisterPaymentRequest registerPaymentRequest) {
            var request = new OrderCreateRequest();
            request.setNotifyUrl("https://your.eshop.com/notify")
                    .setCustomerIp("127.0.0.1")
                    .setMerchantPosId("300746")
                    .setDescription("My digital product")
                    .setCurrencyCode("PLN")
                    .setTotalAmount(15500)
                    .setExtOrderId(UUID.randomUUID().toString())
                    .setBuyer(new Buyer()
                            .setEmail("kuba.doe@example.com")
                            .setFirstName("john")
                            .setLastName("doe")
                            .setLanguage("pl")
                    )
                    .setProducts(Arrays.asList(
                            new Product()
                                    .setName("Nice product")
                                    .setUnitPrice(15500)
                                    .setQuantity(1)
                    ));
            OrderCreateResponse handle = payU.handle(
                    request
            );
            return new PaymentDetails(handle.getExtOrderId());
        }
    }

}
