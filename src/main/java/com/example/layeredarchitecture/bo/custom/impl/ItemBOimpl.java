package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.ItemBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.DAOType;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOimpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOType.ITEM);
    @Override
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        Item item1 = new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        return itemDAO.save(item1);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        Item item1 = new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        return itemDAO.update(item1);
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewId();
    }

    @Override
    public ArrayList<ItemDTO> getItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items =  itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOs = new ArrayList<>();
        for (Item item : items) {
            itemDTOs.add(new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
        }
        return itemDTOs;
    }


}
