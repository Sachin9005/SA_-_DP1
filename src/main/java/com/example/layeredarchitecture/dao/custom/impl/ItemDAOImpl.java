package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString("code");
            String description = rst.getString("description");
            int qtyOnHand = rst.getInt("qtyOnHand");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            items.add(new Item(code,description,unitPrice,qtyOnHand));
        }
        return items;
    }

    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        return resultSet.next();
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        Item item = null;
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        if (rst.next()) {
            String description = rst.getString("description");
            int qtyOnHand = rst.getInt("qtyOnHand");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            item =new Item(code, description, unitPrice, qtyOnHand);
        }
        return item;
    }
}