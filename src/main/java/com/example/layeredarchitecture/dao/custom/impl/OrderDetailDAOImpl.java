package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.OrderDetails;

import java.sql.*;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
     public boolean saveOrderDetails(OrderDetails orderDetail) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", orderDetail.getOid(), orderDetail.getItemCode(), orderDetail.getUnitPrice(), orderDetail.getQty());
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDetails customerDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public OrderDetails search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }
}