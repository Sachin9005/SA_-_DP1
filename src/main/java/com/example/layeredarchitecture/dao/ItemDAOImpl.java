package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.model.ItemDTO;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<ItemDTO> items = new ArrayList<>();
        while (rst.next()) {
            String code = rst.getString("code");
            String description = rst.getString("description");
            int qtyOnHand = rst.getInt("qtyOnHand");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            items.add(new ItemDTO(code,description,unitPrice,qtyOnHand));
        }
        return items;
    }

    @Override
    public void saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
    }

    @Override
    public void deleteItem(String code) throws SQLException, ClassNotFoundException {
        CrudUtil.execute("DELETE FROM Item WHERE code=?",code);
    }

    @Override
    public boolean existsItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        return resultSet.next();
    }

    @Override
    public ResultSet getCode() throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
    }

    @Override
    public ItemDTO serchItem(String code) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = null;
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        if (rst.next()) {
            String description = rst.getString("description");
            int qtyOnHand = rst.getInt("qtyOnHand");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            itemDTO=new ItemDTO(code, description, unitPrice, qtyOnHand);
        }
        return itemDTO;
    }

    @Override
    public ItemDTO getItem(String code) throws SQLException, ClassNotFoundException {
        ItemDTO itemDTO = null;
        ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
        if (rst.next()) {
            String description = rst.getString("description");
            int qtyOnHand = rst.getInt("qtyOnHand");
            BigDecimal unitPrice = rst.getBigDecimal("unitPrice");
            itemDTO=new ItemDTO(code, description, unitPrice, qtyOnHand);
        }
        return itemDTO;
    }
}