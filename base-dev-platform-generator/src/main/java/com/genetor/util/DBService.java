package com.genetor.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBService {


    public static List<Map<String,Object>> queryForList(Connection conn,String sql,Object... values){


        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                ps.setObject(i+1,values[i]);
            }
            rs = ps.executeQuery();
            List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();

            if(rs != null){

                ResultSetMetaData md = rs.getMetaData(); //得到结果集(rs)的结构信息，比如字段数、字段名等
                int columnCount = md.getColumnCount(); //返回此 ResultSet 对象中的列数
                Map<String,Object> rowData = new HashMap<String,Object>();
                while (rs.next()) {
                    rowData = new HashMap(columnCount);
                    for (int i = 1; i <= columnCount; i++) {
                        rowData.put(md.getColumnName(i), rs.getObject(i));
                    }
                    list.add(rowData);
                }
                return list;
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
