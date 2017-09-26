package be.vdab.dao.impl;

import be.vdab.dao.ShopDao;
import be.vdab.entiteiten.Eshop;
import be.vdab.entiteiten.Product;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.jdbc.ConnectionDao.getConnection;

public class ShopDaoImpl implements ShopDao {
    private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl.class.getName());

    @Override
    public List<Eshop> listAllShops() {
        String sql = "select * from eshop;";
        List<Eshop> eshopList = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            con.setAutoCommit(false);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    eshopList.add(new Eshop(rs.getInt("id"), rs.getString("info"), rs.getString("address")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("eshops not found - " + e);
        }
        return eshopList;
    }
}
