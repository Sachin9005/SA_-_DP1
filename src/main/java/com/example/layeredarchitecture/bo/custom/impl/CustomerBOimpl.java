package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.DAOType;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOimpl implements CustomerBO {
    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers =  customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOs = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
            customerDTOs.add(customerDTO);
        }
        return customerDTOs;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        return customerDAO.save(customer);
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getAddress());
        return customerDAO.update(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewId();
    }


}
