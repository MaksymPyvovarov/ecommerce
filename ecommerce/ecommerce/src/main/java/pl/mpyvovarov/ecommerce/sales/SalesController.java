package pl.mpyvovarov.ecommerce.sales;

import org.springframework.web.bind.annotation.*;
import pl.mpyvovarov.ecommerce.sales.reservation.AcceptOfferCommand;
import pl.mpyvovarov.ecommerce.sales.reservation.ReservationDetails;

@RestController
public class SalesController {
    SalesFacade sales;

    public SalesController(SalesFacade sales) {
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    Offer getCurrentOffer() {
        var customerId = getCurrentCustomerId();
        return sales.getCurrentOffer(customerId);
    }

    @PostMapping("/api/add-product/{productId}")
    void addProduct(@PathVariable(name = "productId") String productId) {
        var customerId = getCurrentCustomerId();
        sales.addProduct(customerId, productId);
    }

    @PostMapping("/api/accept-offer")
    ReservationDetails acceptOffer(@RequestBody AcceptOfferCommand acceptOfferRequest) {
        var customerId = getCurrentCustomerId();
        return sales.acceptOffer(customerId, acceptOfferRequest);
    }

    private String getCurrentCustomerId() {
        return "kuba";
    }



}
