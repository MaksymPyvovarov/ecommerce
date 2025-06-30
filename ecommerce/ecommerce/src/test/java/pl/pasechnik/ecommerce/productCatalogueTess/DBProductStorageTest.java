package pl.pasechnik.ecommerce.productCatalogueTess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.pasechnik.productCatalogue.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class DBProductStorageTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @BeforeEach
    void setupDB(){
        jdbcTemplate.execute("Drop table `product_catalogue` if exists");
        var sql = """
                create table  `product_catalogue`(
                id varchar (100) not null,
                name varchar(50) not null,
                description varchar(100) not null,
                price decimal(12,2),
                cover varchar(100),
                PRIMARY KEY(id));
                """;
        jdbcTemplate.execute(sql);
    }
    @Test
    void itQueryDb(){
        var sql= "select now() curr_time";
        var result = jdbcTemplate.queryForObject(sql, String.class);

        assert result.contains("2025");
    }
    @Test
    void itCreatesTables(){
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalogue`", Integer.class);

        assert result == 0;
    }
    @Test
    void itAllowsToInsertIntoTable(){

        var insertSql = """
                INSERT INTO `product_catalogue`(id,name,description)
                Values (?,?,?)
                """;
        jdbcTemplate.update(insertSql,"d1d7f479-6e47-4aa4-88e5-1e6d58359654","product uno","really good one");
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalogue`", Integer.class);

        assert result == 1;
    }
    @Test
    void itAllowsToInsertIntoTableViaParameters(){

        var insertSql = """
                INSERT INTO `product_catalogue`(id,name,description)
                Values (:id,:name,:desc);
                """;
        Map<String, Object> params = new HashMap<>();
        params.put("id","af208e12-cf1e-44d5-9a53-6697b6d9ac67");
        params.put("desc","products");
        params.put("name","producto uno");
        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbc.update(insertSql,params);
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalogue`", Integer.class);

        assert result == 1;
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
    private  ProductStorage thereIsProductCatalogue(){return new DbProductCatalogue(jdbcTemplate);}
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
