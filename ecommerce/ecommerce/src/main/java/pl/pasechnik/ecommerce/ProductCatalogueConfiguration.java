package pl.pasechnik.ecommerce;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pasechnik.productCatalogue.ArrayProductStorage;
import pl.pasechnik.productCatalogue.DbProductCatalogue;
import pl.pasechnik.productCatalogue.ProductCatalogue;
import pl.pasechnik.productCatalogue.ProductStorage;

@Configuration
public class ProductCatalogueConfiguration {
    @Bean
    ProductCatalogue createMyProductCatalogue(ProductStorage storage){
        var catalog= new ProductCatalogue(storage);
        catalog.createProducts("lego 2342","nice one 1");
        catalog.createProducts("lego 2342","nice one 2");
        catalog.createProducts("lego 2342","nice uno  3");
        catalog.createProducts("lego 2342","nice one 4");
        return catalog;
    }
    @Bean
    ProductStorage createMyStorage(JdbcTemplate jdbcTemplate){
        return new DbProductCatalogue(jdbcTemplate);
    }
}
