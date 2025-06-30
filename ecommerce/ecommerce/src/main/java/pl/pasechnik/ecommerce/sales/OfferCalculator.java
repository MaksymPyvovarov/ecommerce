package pl.pasechnik.ecommerce.sales;

import pl.pasechnik.ecommerce.sales.cart.CartItem;

import java.math.BigDecimal;
import java.util.List;

public class OfferCalculator {
    public Offer calculate(List<CartItem> items) {
        // every Xth for free
        // >100PLN -10PLN

        return new Offer(BigDecimal.ZERO,0);
    }
}
