package pl.mpyvovarov.ecommerce.sales;

import java.math.BigDecimal;

public class Offer {
    private final BigDecimal total;
    private final int size;
    public Offer(BigDecimal total, int size) {
        this.total = total;
        this.size = size;

    }

    public BigDecimal getTotal() {
        return total;
    }

    public Integer getItemsCount() {
        return size;
    }
}
