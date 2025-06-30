package pl.mpyvovarov.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mpyvovarov.productCatalogue.Product;
import pl.mpyvovarov.productCatalogue.ProductCatalogue;

import java.util.List;

@RestController
public class ProductCatalogueController {
    ProductCatalogue productCatalog;

    public ProductCatalogueController(ProductCatalogue productCatalog) {
        this.productCatalog = productCatalog;
    }

    @GetMapping("/api/version")
    public String version() {
        return "v0.0.1"; //https://semver.org/
    }

    @GetMapping("/api/products")
    public List<Product> allProducts() {
        return productCatalog.allProducts();
    }



}
