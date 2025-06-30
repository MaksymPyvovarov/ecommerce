package pl.pasechnik.productCatalogue;

import java.util.List;

public class DbProductCatalogue implements ProductStorage {

    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void save(Product newProduct) {

    }

    @Override
    public Product loadProductById(String productId) {
        return null;
    }
}
