package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.entity.Orders;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public Orders search(String newValue) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String orderID) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderID);
        return rst.next();
    }

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Orders order) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",order.getOid(),order.getDate(),order.getCustomerID());
    }

    @Override
    public boolean update(Orders order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


}