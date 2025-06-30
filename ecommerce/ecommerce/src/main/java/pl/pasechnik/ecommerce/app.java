package pl.pasechnik.ecommerce;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pasechnik.productCatalogue.ArrayProductStorage;
import pl.pasechnik.productCatalogue.ProductCatalogue;


@SpringBootApplication
public class app {


    public static void main(String[] args) {
        System.out.println("it works");
        SpringApplication.run(app.class, args);
    }
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    @Bean
    ProductCatalogue createMyProductCatalogue(){
        var catalog= new ProductCatalogue(
                new ArrayProductStorage()
        );
        catalog.createProducts("lego 2342","nice one");
        return catalog;
    }


}
