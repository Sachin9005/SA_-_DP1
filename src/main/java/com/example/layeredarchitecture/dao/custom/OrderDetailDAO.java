package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO {
    boolean saveOrderDetails(String orderId, OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException;
}
