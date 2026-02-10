package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.dto.ItemDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getItems() throws SQLException, ClassNotFoundException;

}
