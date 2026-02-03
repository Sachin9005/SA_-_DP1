package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;

    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;

    boolean existsItem(String code) throws SQLException, ClassNotFoundException;

    ResultSet getCode() throws SQLException, ClassNotFoundException;

    ItemDTO serchItem(String code) throws SQLException, ClassNotFoundException;

    ItemDTO getItem(String code) throws SQLException, ClassNotFoundException;
}
