package be.vdab.dao;

import be.vdab.entiteiten.Order;

import java.sql.SQLException;

public interface BasketDao {

    void addToDB(Order order, int productId, int amount) throws SQLException;

    int getLastId();
}
