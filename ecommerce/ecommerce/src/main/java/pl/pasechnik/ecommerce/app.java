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
        System.out.println("it works,no it does nothing");
        SpringApplication.run(app.class, args);
    }
}
