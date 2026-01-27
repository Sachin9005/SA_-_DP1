package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customers = new ArrayList<>();
        while (rst.next()) {
          String id = rst.getString("id");
          String name = rst.getString("name");
          String address = rst.getString("address");
          customers.add(new CustomerDTO(id, name, address));
        }
        return customers;
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer (id,name, address) VALUES (?,?,?)");
        pstm.setString(1, customerDTO.getId());
        pstm.setString(2, customerDTO.getName());
        pstm.setString(3, customerDTO.getAddress());
        return pstm.executeUpdate() > 0 ;
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setString(3, customerDTO.getId());
        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeUpdate() > 0;
    }

    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, id);
        return pstm.executeQuery().next();
    }

    public ResultSet generateNewId () throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        return connection.createStatement().executeQuery("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
    }

    public CustomerDTO searchCustomer (String newValue) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = null;
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue );
        ResultSet rst = pstm.executeQuery();
        if (rst.next()) {
            customerDTO=new  CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
        }
        return customerDTO;
    }

}