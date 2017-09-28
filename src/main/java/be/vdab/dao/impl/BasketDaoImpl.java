package be.vdab.dao.impl;

import be.vdab.dao.BasketDao;
import be.vdab.entiteiten.Basket;
import be.vdab.entiteiten.Product;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static be.vdab.jdbc.ConnectionDao.getConnection;

public class BasketDaoImpl implements BasketDao {
    private Basket basket = new Basket();
    private static final Logger LOGGER = Logger.getLogger(BasketDaoImpl.class);

    @Override
    public void checkOutBasket(Basket basket) {

    }

    @Override
    public void saveOrUpdateBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void addProductToBasket(Product product, double amount) {
        basket.addToBasket(product, amount);
    }

    @Override
    public void removeProductFromBasket(Product product) {
        basket.removeFromBasket(product);
    }

    @Override
    public Basket getBasket() {
        return basket;
    }

    @Override
    public void clearBasket() {
        basket.getProducten().clear();
    }

    @Override
    public String toString() {
        return basket.toString();
    }

    public void addToDB(String query) {
        /*try (Connection con = getConnection(); PreparedStatement stmt = con.prepareStatement()) {
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
            LOGGER.error("basket could not be added to database" + e);
        }*/
    }
}
