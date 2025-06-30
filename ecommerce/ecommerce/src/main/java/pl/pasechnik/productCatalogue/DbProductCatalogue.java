package pl.pasechnik.productCatalogue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DbProductCatalogue implements ProductStorage {

    private final JdbcTemplate jdbcTemplate;

    public DbProductCatalogue(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> allProducts() {
        //SQL ->Productos
        var sql ="""
                select * from `product_catalogue`
                """;
        var result = jdbcTemplate.query(sql,
                new Object[]{},
                (rs,i)->{
                    var product = new Product(UUID.fromString(rs.getString("ID")),
                            rs.getString("NAME"),
                            rs.getString("Description")
                    );
                    return product;
                });
        return result;
    }

    @Override
    public void save(Product newProduct) {
        //if already existamente

        var insertSql = """
                INSERT INTO `product_catalogue`(id,name,description)
                Values (:id,:name,:desc);
                """;
        Map<String, Object> params = new HashMap<>();
        params.put("id",newProduct.getId());
        params.put("desc",newProduct.getDesc());
        params.put("name",newProduct.getName());
        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbc.update(insertSql,params);
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalogue`", Integer.class);

        assert result == 1;
    }

    @Override
    public Product loadProductById(String productId) {
        //SQL ->Producto
        var sql ="""
                select * from `product_catalogue` where id = ?
                """;
        var result = jdbcTemplate.queryForObject(sql,
                new Object[]{productId},
        (rs,i)->{
            var product = new Product(UUID.fromString(rs.getString("ID")),
                    rs.getString("NAME"),
                    rs.getString("Description")
                    );
        return product;
        });
        return result;
    }
}
