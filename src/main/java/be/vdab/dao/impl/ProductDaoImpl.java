package be.vdab.dao.impl;

import be.vdab.dao.ProductDao;
import be.vdab.entiteiten.Product;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.jdbc.ConnectionDao.getConnection;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class.getName());
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findProducts(String productname) {
        productname = "'%".concat(productname).concat("%'");
        String sql = "select * from products where name like " + productname +" ;";
        addToList(sql);
        return products;
    }

    @Override
    public void sortProducts() {
        String sql = "select * from products;";
        addToList(sql);
    }

    @Override
    public void sortProductsByPriceAsc() {
        String sql = "select * from products order by price asc;";
        addToList(sql);
    }

    @Override
    public void sortProductsByPriceDesc() {
        String sql = "select * from products order by price desc;";
        addToList(sql);
    }

    @Override
    public List<Product> getProducts() {
        String sql = "select * from products;";
        addToList(sql);
        return products;
    }

    @Override
    public Product get(int i) {
        return products.get(i);
    }

    private void addToList(String query) {
        products.clear();
        try (Connection con = getConnection(); Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)) {
            try (ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int stock = rs.getInt("stock");
                    products.add(new Product(id, name, price, stock));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("product not found - " + e);
        }
    }
}
