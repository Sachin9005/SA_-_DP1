package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.dao.custom.impl.*;

public class DAOFactory{
    public static DAOFactory daoFactory ;
    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory==null?new DAOFactory():daoFactory;
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null ;
        }
    }
}
