package pl.pasechnik.ecommerce;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pasechnik.productCatalogue.Product;
import pl.pasechnik.productCatalogue.ProductCatalogue;

import java.util.List;

@RestController
public class ProductCatalogueController {
    ProductCatalogue productCatalogue;
    public ProductCatalogueController(ProductCatalogue productCatalogue){
        this.productCatalogue=productCatalogue;
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @GetMapping("/api/info")
    public String  version(){
        return "v0.0.1";
    }
    @GetMapping("/api/products")
    public List<Product> allProducts() {
        return productCatalogue.allProducts();
    }



}
