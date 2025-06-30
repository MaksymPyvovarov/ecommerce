package pl.pasechnik.ecommerce.productCatalogueTess;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.pasechnik.productCatalogue.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class DBProductStorageTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void itQueryDb(){
        var sql= "select now() curr_time";
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2025");
    }
    @Test
    void itCreatesTables(){
        var sql = """
                create table  `product_catalogue`(
                id varchar (100) not null,
                name varchar(50) not null,
                PRIMARY KEY(id));
                """;
        jdbcTemplate.execute(sql);
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalogue`", Integer.class);

        assert result == 0;
    }

    @Test
    void isSaveAndLoadProduct(){
        //Arrange
        ProductStorage catalogue=thereIsProductCatalogue();
        var product=thereIsProduct();;

        //Act
        catalogue.save(product);
        var loaded=catalogue.loadProductById(product.getId());
        //Assert
        assertEquals(product.getId(),loaded.getId());
        assertEquals(product.getDesc(),loaded.getDesc());
    }
    private  ProductStorage thereIsStorage(){
        return new HashMapProductStorage();
    }
    private  ProductStorage thereIsProductCatalogue(){return new DbProductCatalogue();}
    private Product thereIsProduct(){return new Product(UUID.randomUUID(),"Lego set 8083","nice one");}
    @Test
    void itLoadsAllProducts(){
        var product=thereIsProduct();
        ProductStorage storage = thereIsProductCatalogue();
        storage.save(product);
        List<Product> all = storage.allProducts();
        assertEquals(1,all.size());
    }
}
