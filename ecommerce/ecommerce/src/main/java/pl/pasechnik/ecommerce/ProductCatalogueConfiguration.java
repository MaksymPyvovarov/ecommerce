package pl.pasechnik.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.pasechnik.productCatalogue.ArrayProductStorage;
import pl.pasechnik.productCatalogue.ProductCatalogue;
@Configuration
public class ProductCatalogueConfiguration {
    @Bean
    ProductCatalogue createMyProductCatalogue(){
        var catalog= new ProductCatalogue(
                new ArrayProductStorage()
        );
        catalog.createProducts("lego 2342","nice one 1");
        catalog.createProducts("lego 2342","nice one 2");
        catalog.createProducts("lego 2342","nice uno  3");
        catalog.createProducts("lego 2342","nice one 4");
        return catalog;
    }
}
