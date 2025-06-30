package pl.mpyvovarov.productCatalogue;

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
    public Product loadProductById(String productId) {
        // SQL -> Product
        var sql = "select * from `product_catalog` where id = ?";

        var result = jdbcTemplate.queryForObject(
                sql,
                new Object[]{productId},
                (rs, i) -> {
                    var product = new Product(
                            UUID.fromString(rs.getString("ID")),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    );

                    return product;
                });

        return result;
    }

    @Override
    public void save(Product newProduct) {
        //if already exists
        var insertSql = """
            INSERT INTO `product_catalog` (id, name, description)
            VALUES
                (:id, :name, :desc)
        """;

        Map<String, Object> params = new HashMap<>();
        params.put("id", newProduct.getId());
        params.put("desc", newProduct.getDesc());
        params.put("name", newProduct.getName());

        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);
        namedJdbc.update(insertSql, params);
    }


    @Override
    public List<Product> allProducts() {
        // SQL -> Product
        var sql = "select * from `product_catalog`";

        var result = jdbcTemplate.query(
                sql,
                new Object[]{},
                (rs, i) -> {
                    var product = new Product(
                            UUID.fromString(rs.getString("ID")),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    );

                    return product;
                });

        return result;
    }
}
