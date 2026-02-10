package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.dao.CrudDAO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.OrderDetails;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetails> {
    boolean saveOrderDetails(OrderDetails orderDetail) throws SQLException, ClassNotFoundException;
}
