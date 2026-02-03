package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import java.sql.*;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
     public boolean saveOrderDetails(String orderId,OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", orderId, orderDetail.getItemCode(), orderDetail.getUnitPrice(), orderDetail.getQty());
    }
}