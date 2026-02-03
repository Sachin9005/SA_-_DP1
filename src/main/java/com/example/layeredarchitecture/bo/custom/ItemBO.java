package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.ItemDTO;
import java.sql.SQLException;

public interface ItemBO {
    boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;

}
