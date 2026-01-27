package com.example.layeredarchitecture.dao;

import java.sql.SQLException;
import java.time.LocalDate;

public interface OrderDAO {
    String generateOderId() throws SQLException, ClassNotFoundException;

    boolean existOrderID(String orderID) throws SQLException, ClassNotFoundException;

    boolean saveOrder(String orderId,String customerId , LocalDate date) throws SQLException, ClassNotFoundException;
}
