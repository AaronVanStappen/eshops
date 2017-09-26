package be.vdab.dao.impl;

import be.vdab.dao.ProductDao;
import be.vdab.entiteiten.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.jdbc.ConnectionDao.getConnection;

public class ProductDaoImpl implements ProductDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class.getName());

    @Override
    public List<Product> findProducts(String productname) {
        String sql = "select * from products where name like ?;";
        List<Product> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            productname = "%".concat(productname).concat("%");
            stmt.setString(1, productname);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    products.add(new Product(rs.getInt("id"), rs.getString("name"),
                            rs.getDouble("price"), rs.getInt("stock")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("product not found - " + e);
        }
        return products;
    }
}
