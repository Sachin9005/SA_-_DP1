package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.bo.custom.SuperBO;
import com.example.layeredarchitecture.bo.custom.impl.CustomerBOimpl;
import com.example.layeredarchitecture.bo.custom.impl.ItemBOimpl;
import com.example.layeredarchitecture.bo.custom.impl.PlaceOrderBOimpl;

public class BOFactory {
    public static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {return boFactory == null ? new BOFactory() : boFactory;}

    public enum BOType {
        CUSTOMER,ITEM, PLACEORDER;
    }

    public SuperBO getBO(BOType type) {
        switch (type){
            case CUSTOMER:
                return new CustomerBOimpl();
            case ITEM:
                return new ItemBOimpl();
            case PLACEORDER:
                return new PlaceOrderBOimpl();
            default:
                return null;
        }
    }
}
