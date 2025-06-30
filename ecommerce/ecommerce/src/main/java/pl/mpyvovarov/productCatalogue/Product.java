package pl.mpyvovarov.productCatalogue;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID uuid;
    private final String name;
    private final String desc;
    private BigDecimal price;
    private String url;


    public Product(UUID uuid, String name, String desc) {

        this.uuid = uuid;
        this.name = name;
        this.desc = desc;

    }

    public String getId() {
        return uuid.toString();
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setImage(String url) {
        this.url=url;
    }
    public String getImage() {
        return url;
    }

    public void changePrice(BigDecimal bigDecimal) {
        this.price=bigDecimal;
    }
}
