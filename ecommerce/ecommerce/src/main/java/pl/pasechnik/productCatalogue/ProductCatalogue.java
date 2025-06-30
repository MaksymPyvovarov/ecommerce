package pl.pasechnik.productCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProductCatalogue {
    ProductStorage productStorage;

    public ProductCatalogue(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String createProducts(String name, String description) {
        var uuid = UUID.randomUUID();

        var newProduct = new Product(
                uuid,
                name,
                description
        ); // DOMAIN

        this.productStorage.save(newProduct); //TECH

        return newProduct.getId();
    }

    public Product loadProductById(String productId) {
        return productStorage.loadProductById(productId);
    }

    public void changePrice(String productId, BigDecimal bigDecimal) {
        var product = productStorage.loadProductById(productId);

        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) { //DOMAIN
            throw new InvalidPriceExeption();
        }

        product.changePrice(bigDecimal);
    }

    public void changeImage(String productId, String url) {
        var product = productStorage.loadProductById(productId);
        product.setImage(url); // DOMAIN
    }
}
