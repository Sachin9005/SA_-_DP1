package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.PlaceOrderBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.DAOType;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.dto.OrderDTO;
import com.example.layeredarchitecture.dto.OrderDetailDTO;
import com.example.layeredarchitecture.entity.Customer;
import com.example.layeredarchitecture.entity.Item;
import com.example.layeredarchitecture.entity.OrderDetails;
import com.example.layeredarchitecture.entity.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOimpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getInstance().getDAO(DAOType.ORDER);
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOType.ITEM);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getInstance().getDAO(DAOType.ORDERDETAILS);

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
    }

    @Override
    public boolean existOrder(String orderID) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(orderID);
    }

    @Override
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        Orders order = new Orders(orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId());
        return orderDAO.save(order);
    }

    @Override
    public boolean saveOrderDetails(String orderId, OrderDetailDTO orderDetail) throws SQLException, ClassNotFoundException {
        OrderDetails orderDetails = new OrderDetails(orderId,orderDetail.getItemCode(),orderDetail.getUnitPrice(),orderDetail.getQty());
        return orderDetailDAO.saveOrderDetails(orderDetails);
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
        Item item =  itemDAO.search(code);
        ItemDTO itemDTO = new ItemDTO(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        return itemDTO;
    }

   @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> item =  itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        for (Item item1 : item) {
            itemDTOS.add(new ItemDTO(item1.getCode(),item1.getDescription(),item1.getUnitPrice(),item1.getQtyOnHand()));
        }
        return itemDTOS;
    }

    @Override
    public CustomerDTO search(String newValue) throws SQLException, ClassNotFoundException {
        Customer customer =  customerDAO.search(newValue);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
        return customerDTO;
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers =  customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDTOS;
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        Item item1 = new Item(item.getCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        return itemDAO.update(item1);
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        Connection connection = null;
        try {
            connection = DBConnection.getDbConnection().getConnection();
            /*if order id already exist*/
            if (existOrder(orderId)) {
                return false;
            }
            connection.setAutoCommit(false);

            boolean isOrderSave = saveOrder(new OrderDTO(orderId,customerId,orderDate));
            if (!isOrderSave) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {

                boolean isOrderDetailSaved= saveOrderDetails(orderId,detail);

                if (!isOrderDetailSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());
                boolean isQtyUpdate = updateItem(item);
                if (!isQtyUpdate) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ItemDTO findItem(String code) {
         try {
            return searchItem(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
