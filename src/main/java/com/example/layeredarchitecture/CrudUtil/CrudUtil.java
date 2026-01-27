package com.example.layeredarchitecture.CrudUtil;

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
            boolean result = stm.executeUpdate(sql)>0;
            return (T) (Boolean) result;
        }
    }
}
