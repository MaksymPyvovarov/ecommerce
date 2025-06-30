package pl.pasechnik.productCatalogue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProductCatalogue {
    static ArrayProductStorage arrayListProductStorage;
    public ProductCatalogue(ArrayProductStorage ArrayProductStorage) {
        this.arrayListProductStorage= ArrayProductStorage;
    }
    public static List<Product> allProducts(){
        return arrayListProductStorage.allProducts();//TEXH
    }
    public String createProducts(String name, String desc) {
        var uuid= UUID.randomUUID();
        var newProduct =new Product(
                uuid,
                name,
                desc
        );//DOMAIN
        this.arrayListProductStorage.save(newProduct);//TECH
        return newProduct.getId();
    }

    public Product loadProductById(String productId) {
        return arrayListProductStorage.loadProductById(productId);
    }

    public void changePrice(String productId,BigDecimal bigDecimal) {
        var product = loadProductById(productId);
        if(bigDecimal.compareTo(BigDecimal.ZERO)>=0){//DOMAIN
            throw new InvalidPriceExeption();
        }
        product.changePrice(bigDecimal);
    }

    public void changeImage(String productId, String url) {
        var product =loadProductById((productId));

        product.setImage(url);//DOMAIN
    }
}
