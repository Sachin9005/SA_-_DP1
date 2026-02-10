package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CrudUtil;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst= CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customers = new ArrayList<>();
        while (rst.next()) {
          String id = rst.getString("id");
          String name = rst.getString("name");
          String address = rst.getString("address");
          customers.add(new Customer(id, name, address));
        }
        return customers;
    }

    @Override
    public boolean save(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
    }

    @Override
    public boolean update(Customer customerDTO) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?",customerDTO.getName(),customerDTO.getAddress(),customerDTO.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        return rst.next();
    }

    @Override
    public String generateNewId () throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public Customer search(String newValue) throws SQLException, ClassNotFoundException {
        Customer customerDTO = null;
        ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);
        if (rst.next()) {
            customerDTO=new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"));
        }
        return customerDTO;
    }
}