package pl.pasechnik.productCatalogue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HashMapProductStorage implements ProductStorage {
    private List<Product> products;

    public HashMapProductStorage() {
        this.products = new ArrayList<>();
    }

    public List<Product> allProducts(){
        return Collections.unmodifiableList((products));//TEXH
    }


    public void save(Product newProduct) {
        this.products.add(newProduct);
    }

    public Product loadProductById(String productId) {

        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .get();//TECH

    }
}
