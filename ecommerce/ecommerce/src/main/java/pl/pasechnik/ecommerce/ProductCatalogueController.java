package pl.pasechnik.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pasechnik.productCatalogue.Product;
import pl.pasechnik.productCatalogue.ProductCatalogue;

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
