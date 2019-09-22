package com.bdp.jdbc.db;

import java.util.HashMap;
import java.util.Map;

public class WhereResult {

    private String sql;
    private Map<String,Object> wheres= new HashMap<>();


    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Map<String, Object> getWheres() {
        return wheres;
    }

    public void setWheres(Map<String, Object> wheres) {
        this.wheres = wheres;
    }
}
