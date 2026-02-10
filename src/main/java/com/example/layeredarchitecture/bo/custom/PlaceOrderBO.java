package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
    boolean existOrder(String orderID) throws SQLException, ClassNotFoundException;
    boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException;
    boolean saveOrderDetails(String orderId, OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException;
    ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException;
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
    CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails);
}
