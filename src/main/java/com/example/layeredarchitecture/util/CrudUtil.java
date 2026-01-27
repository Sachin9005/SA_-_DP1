package com.example.layeredarchitecture.util;

import com.example.layeredarchitecture.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {
    public static <T> T execute(String sql,Object...args) throws SQLException, ClassNotFoundException {
        Connection conn = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = conn.prepareStatement(sql);
        for(int i = 0; i < args.length; i++){
            stm.setObject(i+1, args[i]);
                    }
        if (sql.startsWith("SELECT") || sql.startsWith("select")){
            ResultSet rst = stm.executeQuery();
            return (T) rst;
        }else {
            int result = stm.executeUpdate(sql);
            boolean rest = result > 0 ;
            return (T) (Boolean) rest;
        }
    }
}
